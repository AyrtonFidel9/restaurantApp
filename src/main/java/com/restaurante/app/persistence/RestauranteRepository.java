package com.restaurante.app.persistence;

import com.restaurante.app.persistence.crud.iRestauranteCrudRepository;
import com.restaurante.app.persistence.models.Restaurante;
import com.restaurante.app.persistence.mapper.iRestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.restaurante.app.domain.repository.iRestaurantRepository;
import com.restaurante.app.domain.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteRepository implements iRestaurantRepository{
    @Autowired
    private iRestauranteCrudRepository restauranteCrudRepository;
    @Autowired
    private iRestaurantMapper mapper;

    @Override
    public List<Restaurant> getAll(){
        List<Restaurante> rest = (List<Restaurante>) restauranteCrudRepository.findAll();
        return mapper.toRestaurants(rest);
    }

    @Override
    public Optional<Restaurant> getById(int idRestaurante){
        return restauranteCrudRepository.findById(idRestaurante).map(rest -> mapper.toRestaurant(rest));
    }

    @Override
    public Restaurant save (Restaurant restaurant){
        Restaurante restaurante1 = restauranteCrudRepository.save(mapper.toRestaurante(restaurant));
        return mapper.toRestaurant(restaurante1);
    }
    @Override
    public void delete (int idRestaurant){
        restauranteCrudRepository.deleteById(idRestaurant);
    }
}
