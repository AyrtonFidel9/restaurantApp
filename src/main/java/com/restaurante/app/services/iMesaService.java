package com.restaurante.app.services;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.entity.TipoMesas;

import java.util.List;

public interface iMesaService{
    MesaDTO ingresarMesa(MesaDTO mesaDTO);
    void eliminarMesa(int idMesa);

    MesaDTO buscarMesa(int idMesa);

    List<MesaDTO> obtenerMesas();

    MesaDTO cambiarEstado(int idMesa, boolean estado);

    MesaDTO actualizarMesa(int idMesa, MesaDTO mesaDTO);

    TipoMesas getMesaType(int numAsientos);
}
