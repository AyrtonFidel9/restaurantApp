package com.restaurante.app.dto;

import com.restaurante.app.entity.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

public class RestauranteDTO {
    private int idRestaurante;

    private String nombre;

    private int cantMesas;

    private String propietario;

    private Set<Mesa> mesas;

    private Set<Usuario> usuarios;

    private Set<Reserva> reservas;

    private Set<Menu> menus;

    private Set<Venta> ventas;

    private Set<Pedido> pedidos;
}
