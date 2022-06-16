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
            @Mapping(source = "restaurante", target = "restauranteDTO"),
            @Mapping(source = "mesas", target = "mesaDTO"),
            @Mapping(source = "usuario", target = "usuarioDTO"),
            @Mapping(source = "ventas", target = "ventas"),
            @Mapping(source = "estadoPedido", target = "estadoPedido")
    })
    PedidoDTO toPedidoDTO(Pedido pedido);

    List<PedidoDTO> toPedidosDTO(List<Pedido> pedidos);

    @InheritInverseConfiguration
    Pedido toPedido (PedidoDTO pedido);
}
