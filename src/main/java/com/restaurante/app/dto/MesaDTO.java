package com.restaurante.app.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;


@Data
@Getter
@Setter
public class MesaDTO {
    private int idMesa;

    private int idRestaurante;

    @Min(value=1, message = "el número debe ser positivo y diferente de 0")
    private int capacidad;

    private com.restaurante.app.entity.TipoMesas tipo;

    private boolean estado;

    @NotNull(message = "Debe ingresar el nombre de la mesa")
    @NotBlank(message = "Se necesita el nombre de la mesa")
    @NotEmpty
    private String nombre;
}
