package com.restaurante.app.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservaMesaKey implements Serializable {
    @Column(name = "idReserva")
    private int idReserva;
    @Column(name = "idMesa")
    private int idMesa;

    public ReservaMesaKey(){}

    public ReservaMesaKey(int idReserva, int idMesa) {
        this.idReserva = idReserva;
        this.idMesa = idMesa;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservaMesaKey)) return false;
        ReservaMesaKey that = (ReservaMesaKey) o;
        return getIdReserva() == that.getIdReserva() && getIdMesa() == that.getIdMesa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdReserva(), getIdMesa());
    }
}
