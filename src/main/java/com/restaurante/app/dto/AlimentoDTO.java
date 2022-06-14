package com.restaurante.app.dto;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.TipoMenu;
import lombok.Data;

import java.util.Set;

@Data
public class AlimentoDTO {
    private int idAlimento;

    private int idMenu;

    private byte[] imagen;

    private String nombre;

    private String descripcion;

    private float precio;

    private boolean disponibilidad;

    private TipoMenu tipo;

    private Set<DetallePedido> DetallePedidos;
}
