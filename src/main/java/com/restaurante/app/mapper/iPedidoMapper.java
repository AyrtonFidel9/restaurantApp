package com.restaurante.app.mapper;

import com.restaurante.app.dto.PedidoDTO;
import com.restaurante.app.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iPedidoMapper {
    @Mappings({
            @Mapping(source = "id", target = "idPedido"),
            @Mapping(source = "restaurante.id", target = "idRestaurante"),
            @Mapping(source = "mesas.id", target = "idMesa"),
            @Mapping(source = "usuario.id", target = "idUsuario"),
            @Mapping(source = "ventas.id", target = "idVentas"),
            @Mapping(source = "estadoPedido", target = "estadoPedido")
    })
    PedidoDTO toPedidoDTO(Pedido pedido);

    List<PedidoDTO> toPedidosDTO(List<Pedido> pedidos);

    @InheritInverseConfiguration
    Pedido toPedido (PedidoDTO pedido);
}
