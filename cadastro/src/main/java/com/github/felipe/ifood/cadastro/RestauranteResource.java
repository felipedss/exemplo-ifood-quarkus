package com.github.felipe.ifood.cadastro;

import com.github.felipe.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.felipe.ifood.cadastro.dto.RestauranteDTO;
import com.github.felipe.ifood.cadastro.dto.RestauranteMapper;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/restaurantes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    public List<RestauranteDTO> buscar() {
        final Stream<Restaurante> stream = Restaurante.streamAll();
        return stream.map(restaurante -> restauranteMapper.toDto(restaurante)).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response adicionar(AdicionarRestauranteDTO dto) {
        final var restaurante = restauranteMapper.toEntity(dto);
        restaurante.persist();
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Restaurante dto) {
        final Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        restauranteOp.ifPresentOrElse(restaurante -> {
            restaurante.setProprietario(dto.getProprietario());
            restaurante.setNome(dto.getNome());
            restaurante.persist();
        }, NotFoundException::new);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void excluir(@PathParam("id") Long id) {
        final Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        restauranteOp.ifPresentOrElse(restaurante -> Restaurante.deleteById(restaurante.id), NotFoundException::new);
    }

}
