package com.restaurante.app.dto;

import com.restaurante.app.entity.Alimento;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.TipoMenu;
import lombok.Data;

import java.util.Set;

@Data
public class MenuDTO {
    private int id;

    private Restaurante restaurante;

    private String nombre;

    private TipoMenu tipo;

    private Set<Alimento> alimentos;
}
