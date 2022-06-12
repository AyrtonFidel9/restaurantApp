package com.restaurante.app.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.dto.RestauranteDTO;
import java.util.List;

@Mapper(componentModel = "spring")
public interface iRestauranteMapper{
    /*
    @Mappings({
            @Mapping(source = "id", target = "idRestaurant"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "cantMesas", target = "cantMesas"),
            @Mapping(source = "propietario", target = "propietario"),
            @Mapping(source = "mesas", target = "mesas"),
            @Mapping(source = "usuarios", target = "usuarios"),
            @Mapping(source = "reservas", target = "reservas"),
            @Mapping(source = "menus", target = "menus"),
            @Mapping(source = "ventas", target = "ventas"),
            @Mapping(source = "pedidos", target = "pedidos")
    })
        // source fuente, target, donde quiero llevarlo
    RestauranteDTO toRestauranteDTO(Restaurante restaurante);
    List<RestauranteDTO> toRestaurantesDTO(List<Restaurante> restaurantes);

    @InheritInverseConfiguration
    Restaurante toRestaurante(RestauranteDTO restaurant);*/
}