package com.restaurante.app.repository;

import com.restaurante.app.entity.Alimento;
import org.springframework.data.repository.CrudRepository;

public interface iAlimentoRepository extends CrudRepository<Alimento, Integer> {
}
