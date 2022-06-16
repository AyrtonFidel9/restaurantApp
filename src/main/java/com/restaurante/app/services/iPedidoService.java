package com.restaurante.app.services;

import com.restaurante.app.dto.PedidoDTO;

import java.util.List;

public interface iPedidoService {
    PedidoDTO ingresarPedido(PedidoDTO pedidoDTO);

    void eliminarPedido(int idPedido);

    PedidoDTO buscarPedido(int idPedido);

    List<PedidoDTO> obtenerPedido();

    PedidoDTO actualizarPedido(int idPedido, PedidoDTO pedidoDTO);


}
