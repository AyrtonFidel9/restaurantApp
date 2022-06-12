package com.restaurante.app.dto;

import com.restaurante.app.entity.ReservaMesa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class ReservaDTO{
    private int idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private int idUsuario;
    private int idRestaurante;

    private Set<ReservaMesa> reservaMesas;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Set<ReservaMesa> getReservaMesas() {
        return reservaMesas;
    }

    public void setReservaMesas(Set<ReservaMesa> reservaMesas) {
        this.reservaMesas = reservaMesas;
    }
}
