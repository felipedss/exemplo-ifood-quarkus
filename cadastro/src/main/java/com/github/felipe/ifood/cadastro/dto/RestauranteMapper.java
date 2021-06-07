package com.github.felipe.ifood.cadastro.dto;

import com.github.felipe.ifood.cadastro.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "id", ignore = true)
    Restaurante toEntity(AdicionarRestauranteDTO dto);

    //FIXME Resolver warnings Unmapped target properties: "dataCriacao, dataAtualizacao".
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Restaurante toEntity(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

    RestauranteDTO toDto(Restaurante entity);

}
