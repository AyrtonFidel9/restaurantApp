package com.restaurante.app.dto;

import com.restaurante.app.entity.Reserva;
import com.restaurante.app.entity.ReservaMesaKey;

public class ReservaMesaDTO {
    private ReservaMesaKey id;


    public ReservaMesaKey getId() {
        return id;
    }

    public void setId(ReservaMesaKey id) {
        this.id = id;
    }
}
