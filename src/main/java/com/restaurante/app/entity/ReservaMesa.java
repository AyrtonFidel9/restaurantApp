package com.restaurante.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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
}
