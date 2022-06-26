package com.restaurante.app.dto;

import com.restaurante.app.entity.Alimento;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.TipoMenu;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private int id;

    private int idRestaurante;
    @NotBlank(message = "Se necesita el nombre del menú")
    @NotEmpty
    @NotNull(message = "Debe ingresar el nombre del menú")
    private String nombre;
    @NotNull(message = "Debe ingresar el tipo de menú")
    private TipoMenu tipo;

}
