package com.restaurante.app.repository;

import com.restaurante.app.entity.Restaurante;
import org.springframework.data.repository.CrudRepository;

public interface iRestauranteRepository extends CrudRepository<Restaurante, Integer> {
}
