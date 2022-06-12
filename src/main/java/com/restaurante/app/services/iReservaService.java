package com.restaurante.app.services;

import com.restaurante.app.dto.ReservaDTO;
import java.util.List;

public interface iReservaService {
    ReservaDTO ingresarReserva (ReservaDTO reservaDTO);
    ReservaDTO actualizarReserva (int idReserva, ReservaDTO reservaDTO);
    void eliminarReserva (int idReserva);
    ReservaDTO buscarReserva (int idReserva);
    List<ReservaDTO> listarReservas ();

}
