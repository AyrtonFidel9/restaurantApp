package com.restaurante.app.dto;

import com.restaurante.app.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Getter
@Setter
public class PedidoDTO {
    private int idPedido;

    private int idRestaurante;

    private int idMesa;

    private int idUsuario;

    private LocalDate fecha;

    private LocalTime hora;

    private EstadoPedido estadoPedido;

    private Set<DetallePedidoDTO> detallePedidos;
}
