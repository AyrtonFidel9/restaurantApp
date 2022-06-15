package com.restaurante.app.dto;
import com.restaurante.app.entity.TipoMesas;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class MesaDTO {
    private int idMesa;
    private int idRestaurante;
    private int capacidad;
    private com.restaurante.app.entity.TipoMesas tipo;
    private boolean estado;
    private String nombre;
}
