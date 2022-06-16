package com.restaurante.app.dto;

import com.restaurante.app.entity.Alimento;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.TipoMenu;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private int id;

    private int idRestaurante;

    private String nombre;

    private TipoMenu tipo;

    private Set<AlimentoDTO> alimentos;
}
