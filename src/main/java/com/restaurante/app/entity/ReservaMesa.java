package com.restaurante.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ReservasMesas")
public class ReservaMesa {

    @EmbeddedId
    ReservaMesaKey id = new ReservaMesaKey();

    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "idReserva")
    @JsonIgnore
    Reserva reserva;


    @ManyToOne
    @MapsId("idMesa")
    @JoinColumn(name = "idMesa")
    Mesa mesas;

    private LocalDate fecha;

    private LocalTime hora;

    public ReservaMesaKey getId() {
        return id;
    }

    public void setId(ReservaMesaKey id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Mesa getMesas() {
        return mesas;
    }

    public void setMesas(Mesa mesas) {
        this.mesas = mesas;
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
}
