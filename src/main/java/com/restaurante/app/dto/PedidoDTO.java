package com.restaurante.app.dto;

import com.restaurante.app.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "El campo fecha es requerido")
    private LocalDate fecha;
    @NotNull(message = "El campo hora es requerido")
    private LocalTime hora;
    @NotNull(message = "El campo estado de pedido es requerido")
    private EstadoPedido estadoPedido;
    @NotNull(message = "El campo detalle pedido es requerido")
    private Set<DetallePedidoDTO> detallePedidos;
}
