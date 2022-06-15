package com.restaurante.app.dto;

import com.restaurante.app.entity.Reserva;
import com.restaurante.app.entity.ReservaMesaKey;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReservaMesaDTO {
    private ReservaMesaKey id;

    private MesaDTO mesa;
}
