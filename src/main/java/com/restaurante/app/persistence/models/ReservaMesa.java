package com.restaurante.app.persistence.models;

import javax.persistence.*;

@Entity
public class ReservaMesa {
    @EmbeddedId
    ReservaMesaKey id;

    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "idReserva")
    Reserva reserva;

    @ManyToOne
    @MapsId("idMesa")
    @JoinColumn(name = "idMesa")
    Mesas mesas;
}
