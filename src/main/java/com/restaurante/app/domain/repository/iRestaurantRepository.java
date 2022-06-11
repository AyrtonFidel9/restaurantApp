package com.restaurante.app.domain.repository;
import com.restaurante.app.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface iRestaurantRepository {
    List<Restaurant> getAll();

    Optional<Restaurant> getById(int idRestaurante);

    Restaurant save (Restaurant restaurante);

    void delete (int idRestaurant);
}
