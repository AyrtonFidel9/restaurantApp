package com.restaurante.app.mapper;

import com.restaurante.app.dto.PedidoDTO;
import com.restaurante.app.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {iDetallePedidoMapper.class})
public interface iPedidoMapper {
    @Mappings({
            @Mapping(source = "id", target = "idPedido"),
            @Mapping(source = "restaurante.id", target = "idRestaurante"),
            @Mapping(source = "mesas.id", target = "idMesa"),
            @Mapping(source = "usuario.id", target = "idUsuario"),
            @Mapping(source = "estadoPedido", target = "estadoPedido"),
            @Mapping(source = "detallePedidos", target = "detallePedidos")
    })
    PedidoDTO toPedidoDTO(Pedido pedido);

    List<PedidoDTO> toPedidosDTO(List<Pedido> pedidos);

    @InheritInverseConfiguration
    @Mapping(target = "ventas", ignore = true)
    Pedido toPedido (PedidoDTO pedido);
}
