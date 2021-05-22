package com.github.felipe.ifood.marketplace;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    private PgPool pgPool;

    @GET
    public Multi<PratoDTO> buscarPratos() {
        return Prato.findAll(pgPool);
    }

    @GET
    @Path("{restauranteId}/pratos")
    public Multi<PratoDTO> buscarPratosDoRestaurante(@PathParam("restauranteId") Long restauranteId) {
        return Prato.findAll(pgPool, restauranteId);
    }


}
