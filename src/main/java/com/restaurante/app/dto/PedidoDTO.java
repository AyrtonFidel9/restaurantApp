package com.restaurante.app.dto;

import com.restaurante.app.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class PedidoDTO {
    private int idPedido;

    private int idRestaurante;

    private int idMesa;

    private int idUsuario;

    private int idVentas;

    private Set<DetallePedidoDTO> DetallePedidos;

    private EstadoPedido estadoPedido;
}
