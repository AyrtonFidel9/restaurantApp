package com.restaurante.app.mapper;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

/*ATENCION CON UNMAPPED TARGET*/
@Mapper(componentModel = "spring")
public interface iUsuarioMapper {

    @Mappings({
            @Mapping(source = "id", target = "idUsuario"),
            @Mapping(source = "restaurante.id",target = "idRestaurante"),
            @Mapping(source = "nombre",target= "nombre"),
            @Mapping(source = "apellido",target= "apellido"),
            @Mapping(source = "cedula",target= "cedula"),
            @Mapping(source = "email",target= "email"),
            @Mapping(source = "password",target= "password"),
            @Mapping(source = "rol",target= "rol")
    })
    UsuarioDTO toUsuarioDTO (Usuario usuario);
    List<UsuarioDTO> toUsuarioDTO (List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    Usuario toUsuario (UsuarioDTO usuario);
}
