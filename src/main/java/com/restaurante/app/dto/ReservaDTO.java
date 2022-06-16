package com.restaurante.app.dto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "El campo fecha es requerido")
    private LocalDate fecha;
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "El campo hora es requerido")
    private LocalTime hora;
    @NotNull(message = "El Usuario es requerido")
    @Min(value=1)
    private int idUsuario;
    @NotNull(message = "El restaurante es requerido")
    @Min(value=1)
    private int idRestaurante;
    @Min(value=25, message = "el número debe ser positivo y diferente de 0, el tiempo mínimo es de 25 min")
    private int duracion;
    @NotNull(message = "Las mesas son requeridas")
    private Set<ReservaMesaDTO> reservaMesas;
}
