package com.restaurante.app.dto;

import com.restaurante.app.entity.Reserva;
import com.restaurante.app.entity.ReservaMesaKey;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class ReservaMesaDTO {
    private ReservaMesaKey id;
    private MesaDTO mesa;
    private LocalDate fecha;
    private LocalTime hora;
}
