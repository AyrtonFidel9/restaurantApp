package com.restaurante.app.repository;

import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.entity.ReservaMesaKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface iReservaMesaRepository extends CrudRepository<ReservaMesa, ReservaMesaKey> {
    Set<ReservaMesa> findReservaMesaByFechaAndHoraBetween(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);
    ReservaMesa findReservaMesaById_IdReservaAndId_IdMesa(int idReserva, int idMesa);
    //Set<ReservaMesa> findReservaMesaById_IdMesaAndFechaGreaterThanEqualAndHoraGreaterThanEqual(int idMesa, LocalDate fecha, LocalTime hora);
    Set<ReservaMesa> findReservaMesaById_IdReserva(int idReserva);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM reservas_mesas where id_Reserva=:id", nativeQuery = true)
    void deleteReservaMesaByIdReserva(@Param("id") int idReserva );
}
