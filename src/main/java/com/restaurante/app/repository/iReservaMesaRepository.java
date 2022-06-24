package com.restaurante.app.repository;

import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.entity.ReservaMesaKey;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface iReservaMesaRepository extends CrudRepository<ReservaMesa, ReservaMesaKey> {
    Set<ReservaMesa> findReservaMesaByFechaAndHoraAfter(LocalDate fecha, LocalTime hora);
    ReservaMesa findReservaMesaById_IdReservaAndId_IdMesa(int idReserva, int idMesa);
    
    Set<ReservaMesa> findReservaMesaById_IdReserva(int idReserva);
}
