package com.github.felipe.ifood.marketplace;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = PratoDTO.class)))
    public Multi<PratoDTO> buscarPratos() {
        return Prato.findAll(pgPool);
    }

    @GET
    @Path("{restauranteId}/pratos")
    public Multi<PratoDTO> buscarPratosDoRestaurante(@PathParam("restauranteId") Long restauranteId) {
        return Prato.findAll(pgPool, restauranteId);
    }


}
