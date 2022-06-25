package com.restaurante.app.services;

import com.restaurante.app.dto.PedidoDTO;
import com.restaurante.app.entity.EstadoPedido;

import java.util.List;

public interface iPedidoService {
    PedidoDTO ingresarPedido(PedidoDTO pedidoDTO);

    void eliminarPedido(int idPedido);

    PedidoDTO buscarPedido(int idPedido);

    List<PedidoDTO> obtenerPedido();

    PedidoDTO actualizarPedido(int idPedido, PedidoDTO pedidoDTO);

    PedidoDTO actualizarEstado(int idPedido, EstadoPedido estadoPedido);
}
