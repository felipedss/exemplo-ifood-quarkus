package com.github.felipe.ifood.marketplace;

import io.vertx.mutiny.sqlclient.Row;

import java.math.BigDecimal;

public class PratoDTO {

    private final Long id;
    private final String nome;
    private final BigDecimal preco;
    private final Restaurante restaurante;

    public PratoDTO(Long id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.restaurante = null;
    }

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

    public static PratoDTO from(Row row) {
        return new PratoDTO(row.getLong("id"),
                row.getString("nome"),
                row.getBigDecimal("preco"));
    }

}
