package com.github.felipe.ifood.cadastro;

import com.github.felipe.ifood.cadastro.common.ConstraintViolationResponse;
import com.github.felipe.ifood.cadastro.dto.*;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @Inject
    PratoMapper pratoMapper;

    @GET
    @Counted(name = "Quantidade buscas restaurantes")
    @SimplyTimed(name = "Tempo simples de busca")
    @Timed(name = "Tempo completo de busca")
    public List<RestauranteDTO> buscar() {
        final Stream<Restaurante> stream = Restaurante.streamAll();
        return stream.map(restaurante -> restauranteMapper.toDto(restaurante)).collect(Collectors.toList());
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Caso o restaurante seja cadastrado com sucesso")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
    public Response adicionar(@Valid AdicionarRestauranteDTO dto) {
        final var restaurante = restauranteMapper.toEntity(dto);
        restaurante.persist();
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, AtualizarRestauranteDTO dto) {
        final Optional<Restaurante> optional = Restaurante.findByIdOptional(id);
        optional.ifPresentOrElse(restaurante -> restauranteMapper.toEntity(dto, restaurante).persist(), NotFoundException::new);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void excluir(@PathParam("id") Long id) {
        final Optional<Restaurante> optional = Restaurante.findByIdOptional(id);
        optional.ifPresentOrElse(restaurante -> Restaurante.deleteById(restaurante.id), NotFoundException::new);
    }

    @GET
    @Path("{id}/pratos")
    public List<PratoDTO> buscarPratos(@PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        Stream<Prato> pratos = Prato.stream("restaurante", restauranteOp.get());
        return pratos.map(p -> pratoMapper.toDto(p)).collect(Collectors.toList());
    }

}
