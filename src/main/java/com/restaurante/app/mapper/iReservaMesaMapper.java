package com.restaurante.app.mapper;

import com.restaurante.app.dto.ReservaMesaDTO;
import com.restaurante.app.entity.ReservaMesa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {iMesaMapper.class})
public interface iReservaMesaMapper {
    @Mappings({
            @Mapping(source = "mesas.id", target = "id.idMesa"),
            @Mapping(source = "reserva.id", target = "id.idReserva"),
            @Mapping(source = "mesas", target = "mesa"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "hora", target = "hora")
    })
    ReservaMesaDTO toReservaMesaDTO (ReservaMesa reservaMesa);
    List<ReservaMesaDTO> toReservasMesasDTO (List<ReservaMesa> reservaMesaList);

    @InheritInverseConfiguration
    ReservaMesa toReservaMesa(ReservaMesaDTO reservaMesaDTO);
}
