package com.restaurante.app.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReservaMesaKey implements Serializable {
    @Column(name = "idReserva")
    private int idReserva;
    @Column(name = "idMesa")
    private int idMesa;
}
