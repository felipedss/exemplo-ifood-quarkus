package com.github.felipe.ifood.marketplace;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.math.BigDecimal;

public class Prato {

    private Long id;

    private String nome;

    private BigDecimal preco;

    private Restaurante restaurante;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public static Multi<PratoDTO> findAll(PgPool pgPool) {
        final Uni<RowSet<Row>> rowSet = pgPool.preparedQuery("select * from prato ").execute();
        return uniToMulti(rowSet);
    }

    public static Multi<PratoDTO> findAll(PgPool pgPool, Long restauranteId) {
        final Uni<RowSet<Row>> rowSet = pgPool.preparedQuery("select * from prato where restaurante_id = $1")
                .execute(Tuple.of(restauranteId));
        return uniToMulti(rowSet);
    }

    private static Multi<PratoDTO> uniToMulti(Uni<RowSet<Row>> rowSet) {
        return rowSet.onItem()
                .transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(PratoDTO::from);
    }

}
