package com.restaurante.app.dto;

import com.restaurante.app.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class PedidoDTO {
    private int id;

    private Restaurante restaurante;

    private Mesa mesas;

    private Usuario usuario;

    private Venta ventas;

    private Set<DetallePedido> DetallePedidos;

    private EstadoPedido estadoPedido;
}
