package com.restaurante.app.mapper;

import com.restaurante.app.dto.DetallePedidoDTO;
import com.restaurante.app.entity.DetallePedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iDetallePedidoMapper {
    @Mappings({
            @Mapping(source = "id.idPedido", target = "idPedido"),
            @Mapping(source = "id.idAlimentos", target = "idAlimento"),
            @Mapping(source = "cantidadAlimento", target = "cantidadAlimento"),
            @Mapping(source = "subtotal", target = "subTotal")
    })
    DetallePedidoDTO toDetallePedidoDTO (DetallePedido detallePedido);
    List<DetallePedidoDTO> toDetallesPedidosDTO (List<DetallePedido> detallePedidos);

    @InheritInverseConfiguration
    DetallePedido toDetallePedido (DetallePedidoDTO detallePedido);
    List<DetallePedido> toDetallesPedidos (List<DetallePedidoDTO> detallePedido);
}
