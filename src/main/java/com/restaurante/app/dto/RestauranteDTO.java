package com.restaurante.app.dto;

import com.restaurante.app.entity.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    private int idRestaurante;

    private String nombre;

    private int cantMesas;

    private String propietario;

    private LocalTime horaApertura;

    private LocalTime horaCierre;

    private Set<Mesa> mesas;

    private Set<Usuario> usuarios;

    private Set<Reserva> reservas;

    private Set<Menu> menus;

    private Set<Venta> ventas;

    private Set<Pedido> pedidos;

}
