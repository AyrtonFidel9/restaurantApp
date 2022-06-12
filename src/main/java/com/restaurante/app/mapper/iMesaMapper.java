package com.restaurante.app.mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.restaurante.app.entity.Mesa;
import com.restaurante.app.dto.MesaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iMesaMapper {
    @Mappings({
            @Mapping(source = "id", target = "idMesa"),
            @Mapping(source = "restaurante.id", target = "idRestaurante"),
            @Mapping(source = "capacidad", target = "capacidad"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "tipo", target = "tipo"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    MesaDTO toMesaDTO (Mesa mesa);
    List<MesaDTO> toMesasDTO(List<Mesa> mesas);

    @InheritInverseConfiguration
    Mesa toMesa (MesaDTO mesa);
}
