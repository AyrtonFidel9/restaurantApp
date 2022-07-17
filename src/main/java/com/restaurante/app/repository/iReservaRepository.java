package com.restaurante.app.repository;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.entity.Reserva;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.List;

public interface iReservaRepository extends CrudRepository<Reserva, Integer> {
    int countByUsuarioIdAndFecha (int idUsuario, LocalDate fecha);

    List<Reserva> findReservaByUsuarioId (int idUsuario);
}
