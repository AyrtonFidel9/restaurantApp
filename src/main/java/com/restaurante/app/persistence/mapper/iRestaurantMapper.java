package com.restaurante.app.persistence.mapper;

import com.restaurante.app.domain.Restaurant;
import com.restaurante.app.persistence.models.Restaurante;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface iRestaurantMapper{
    @Mappings({
            @Mapping(source = "id", target = "idRestaurant"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "cantMesas", target = "numTables"),
            @Mapping(source = "propietario", target = "owner"),
            @Mapping(source = "mesas", target = "tables"),
            @Mapping(source = "usuarios", target = "users"),
            @Mapping(source = "reservas", target = "reservations"),
            @Mapping(source = "menus", target = "orders"),
            @Mapping(source = "ventas", target = "menus"),
            @Mapping(source = "pedidos", target = "sales")
    })
    // source fuente, target, donde quiero llevarlo
    Restaurant toRestaurant(Restaurante restaurante);
    List<Restaurant> toRestaurants(List<Restaurante> restaurantes);

    @InheritInverseConfiguration
    Restaurante toRestaurante(Restaurant restaurant);
}
