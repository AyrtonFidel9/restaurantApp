package com.restaurante.app.mapper;

import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.Venta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iVentaMapper {
    @Mappings({
            @Mapping(source = "id",target = "idVenta"),
            @Mapping(source = "usuario.id",target = "idUsuario"),
            @Mapping(source = "pedido.id",target = "idPedido"),
            @Mapping(source = "restaurante.id",target = "idRestaurante"),
            @Mapping(source = "formaDePago",target = "formaDePago"),
            @Mapping(source = "fecha",target = "fecha"),
            @Mapping(source = "calificacion",target = "calificacion"),
            @Mapping(source = "impuestos",target = "impuestos"),
            @Mapping(source = "propina",target = "propina"),
            @Mapping(source = "total",target = "total")

    })

    VentaDTO toVentaDTO (Venta venta);
    List<VentaDTO> toVentaDTO (List<Venta> ventas);
    @InheritInverseConfiguration
    Venta toVenta (VentaDTO ventaDTO);
    List<Venta> toVenta (List<VentaDTO> ventaDTOList);


}
