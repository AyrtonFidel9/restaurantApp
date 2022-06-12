package com.restaurante.app.mapper;

import com.restaurante.app.entity.Reserva;
import com.restaurante.app.dto.ReservaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iReservaMapper {
    @Mappings({
            @Mapping(source = "id",target = "idReserva"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "hora", target = "hora"),
            @Mapping(source = "restaurante.id", target = "idRestaurante"),
            @Mapping(source = "usuario.id", target = "idUsuario"),
            @Mapping(source = "reservaMesas", target = "reservaMesas")
    })
    ReservaDTO toReservaDTO (Reserva reserva);
    List<ReservaDTO> toReservasDTO (List<Reserva> reservas);

    @InheritInverseConfiguration
    Reserva toReserva (ReservaDTO reservaDTO);
    List<Reserva> toReservas (List<ReservaDTO> reservaDTOList);
}
