package com.restaurante.app.dto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO{
    private int idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private int idUsuario;
    private int idRestaurante;
    private int duracion;
    private Set<ReservaMesaDTO> reservaMesas;
}
