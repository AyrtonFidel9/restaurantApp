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
            @Mapping(source = "pedido.id", target = "id.idPedido"),
            @Mapping(source = "alimento.id", target = "id.idAlimentos"),
            @Mapping(source = "alimentos", target = "alimeto"),
            @Mapping(source = "cantidadAlimento", target = "cantidadAlimento"),
            @Mapping(source = "subtotal", target = "SubTotal")
    })
    DetallePedidoDTO toDetallePedidoDTO (DetallePedido detallePedido);
    List<DetallePedidoDTO> toDetallesPedidosDTO (List<DetallePedido> detallePedidos);

    @InheritInverseConfiguration
    DetallePedido toDetallePedido (DetallePedidoDTO detallePedido);
    List<DetallePedido> toDetallesPedidos (DetallePedido detallePedido);
}
