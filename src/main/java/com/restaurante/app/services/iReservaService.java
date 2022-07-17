package com.restaurante.app.services;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.entity.Reserva;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.List;

public interface iReservaService {
    ReservaDTO ingresarReserva (ReservaDTO reservaDTO);
    ReservaDTO actualizarReserva (int idReserva, ReservaDTO reservaDTO);
    void eliminarReserva (int idReserva);
    ReservaDTO buscarReserva (int idReserva);
    List<ReservaDTO> listarReservas ();
    Set<ReservaMesa> createListReservasMesas (Reserva reserva,ReservaDTO reservaDTO);

    List<ReservaDTO> listaReservasByUserId(int idUser);

}
