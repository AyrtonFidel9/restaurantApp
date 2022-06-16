package com.restaurante.app.services;

import com.restaurante.app.dto.AlimentoDTO;
import com.restaurante.app.entity.TipoMenu;

import java.util.List;

public interface iAlimentoService {
    AlimentoDTO ingresarAlimento(AlimentoDTO alimentoDTO);

    void eliminarAlimento(int idAlimento);

    AlimentoDTO buscarAlimento(int idAlimento);

    List<AlimentoDTO> obtenerAlimento();

    AlimentoDTO cambiarDisponibilidad(int idAlimento, boolean disponibilidad);

    AlimentoDTO actualizarAlimento(int idAlimento, AlimentoDTO alimentoDTO);

    List<AlimentoDTO> obtenerAlimentoPorTipo(TipoMenu tipo);
}
