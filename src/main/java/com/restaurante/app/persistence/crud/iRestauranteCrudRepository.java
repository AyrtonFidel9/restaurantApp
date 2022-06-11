package com.restaurante.app.persistence.crud;

import com.restaurante.app.persistence.models.Restaurante;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface iRestauranteCrudRepository extends CrudRepository<Restaurante, Integer> {
}
