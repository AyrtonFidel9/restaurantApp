package com.restaurante.app.dto;

import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.entity.Pedido;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.TipoMesas;

import java.util.Set;

public class MesaDTO {
    private int idMesa;
    private Restaurante restaurante;
    private int capacidad;
    private com.restaurante.app.entity.TipoMesas tipo;
    private Pedido pedido;
    private Set<ReservaMesa> reservaMesas;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public TipoMesas getTipo() {
        return tipo;
    }

    public void setTipo(TipoMesas tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Set<ReservaMesa> getReservaMesas() {
        return reservaMesas;
    }

    public void setReservaMesas(Set<ReservaMesa> reservaMesas) {
        this.reservaMesas = reservaMesas;
    }
}
