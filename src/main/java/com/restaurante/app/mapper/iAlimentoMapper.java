package com.restaurante.app.mapper;

import com.restaurante.app.dto.AlimentoDTO;
import com.restaurante.app.entity.Alimento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iAlimentoMapper {
    @Mappings({
            @Mapping(source = "id", target = "idAlimento"),
            @Mapping(source = "menu.id", target = "idMenu"),
            @Mapping(source = "imagen", target = "imagen"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "disponibilidad", target = "disponibilidad"),
            @Mapping(source = "tipo", target = "tipo"),

    })
    AlimentoDTO toAlimentoDTO(Alimento alimento);
    List<AlimentoDTO> toAlimentosDTO(List<Alimento> alimentos);

    @InheritInverseConfiguration
    Alimento toAlimento(AlimentoDTO alimento);
}
