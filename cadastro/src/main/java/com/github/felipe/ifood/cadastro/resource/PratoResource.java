package com.github.felipe.ifood.cadastro.resource;

import com.github.felipe.ifood.cadastro.dto.AdicionarPratoDTO;
import com.github.felipe.ifood.cadastro.dto.AtualizarPratoDTO;
import com.github.felipe.ifood.cadastro.dto.PratoMapper;
import com.github.felipe.ifood.cadastro.model.Prato;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/pratos")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PratoResource {

    @Inject
    PratoMapper pratoMapper;

    @GET
    public List<Prato> buscar() {
        return Prato.listAll();
    }

    @POST
    @Transactional
    public Response adicionar(AdicionarPratoDTO dto) {
        final var prato = pratoMapper.toEntity(dto);
        prato.persist();
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, AtualizarPratoDTO dto) {
        final Optional<Prato> optional = Prato.findByIdOptional(id);
        optional.ifPresentOrElse(prato -> {
            final var entity = pratoMapper.toEntity(dto, prato);
            entity.persist();
        }, NotFoundException::new);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void excluir(@PathParam("id") Long id) {
        final Optional<Prato> optional = Prato.findByIdOptional(id);
        optional.ifPresentOrElse(prato -> Prato.deleteById(prato.id), NotFoundException::new);
    }


}
