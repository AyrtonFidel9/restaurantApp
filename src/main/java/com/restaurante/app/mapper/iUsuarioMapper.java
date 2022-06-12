package com.restaurante.app.mapper;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Usuario;
import org.mapstruct.*;

import java.util.List;

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
    Usuario toUsuario (UsuarioDTO usuario);
}
