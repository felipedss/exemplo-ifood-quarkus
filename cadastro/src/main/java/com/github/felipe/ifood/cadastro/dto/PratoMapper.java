package com.github.felipe.ifood.cadastro.dto;

import com.github.felipe.ifood.cadastro.model.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    @Mapping(target = "id", ignore = true)
    Prato toEntity(AdicionarPratoDTO dto);

    @Mapping(target = "id", ignore = true)
    Prato toEntity(AtualizarPratoDTO dto, @MappingTarget Prato prato);

    PratoDTO toDto(Prato entity);
}
