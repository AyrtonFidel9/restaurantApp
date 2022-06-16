package com.restaurante.app.mapper;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.entity.Menu;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface iMenuMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "restaurante.id", target = "idRestaurante"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "tipo", target = "tipo")
    })
    MenuDTO toMenuDTO(Menu menu);

    @InheritInverseConfiguration
    Menu toMenu (MenuDTO menu);
}
