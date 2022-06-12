package com.restaurante.app.repository;

import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.entity.ReservaMesaKey;
import org.springframework.data.repository.CrudRepository;

public interface iReservaMesaRepository extends CrudRepository<ReservaMesa, ReservaMesaKey> {

}
