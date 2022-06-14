package com.restaurante.app.dto;

import com.restaurante.app.entity.Reserva;
import com.restaurante.app.entity.ReservaMesaKey;

public class ReservaMesaDTO {
    private ReservaMesaKey id;

    private MesaDTO mesa;

    public ReservaMesaKey getId() {
        return id;
    }

    public void setId(ReservaMesaKey id) {
        this.id = id;
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }
}
