package com.restaurante.app.dto;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.TipoMenu;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class AlimentoDTO {
    private int idAlimento;

    private int idMenu;

    private byte[] imagen;

    @NotNull(message = "El campo nombre es requerido")
    @Size(min=2, max=25)
    private String nombre;

    @NotNull(message = "El campo descripcion es requerido")
    private String descripcion;

    @DecimalMin("0.01")
    @Digits(integer=6, fraction=2)
    private float precio;

    private boolean disponibilidad;

    private TipoMenu tipo;
}
