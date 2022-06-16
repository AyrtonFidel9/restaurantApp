package com.restaurante.app.repository;

import com.restaurante.app.entity.Reserva;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;

public interface iReservaRepository extends CrudRepository<Reserva, Integer> {
    int countByUsuarioIdAndFecha (int idUsuario, LocalDate fecha);
}
