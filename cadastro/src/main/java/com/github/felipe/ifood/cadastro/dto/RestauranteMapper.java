package com.github.felipe.ifood.cadastro.dto;

import com.github.felipe.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    Restaurante toEntity(AdicionarRestauranteDTO dto);

    RestauranteDTO toDto(Restaurante entity);

}
