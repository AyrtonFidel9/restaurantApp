package com.restaurante.app.repository;

import com.restaurante.app.entity.Mesa;
import org.springframework.data.repository.CrudRepository;

public interface iMesaRepository extends CrudRepository<Mesa, Integer> {
    int countMesaByRestauranteId(int idRestaurante);
}
