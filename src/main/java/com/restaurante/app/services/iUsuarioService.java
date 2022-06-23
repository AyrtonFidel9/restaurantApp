package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface iUsuarioService extends UserDetailsService  {
    UsuarioDTO ingresarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarUsuario(int idUsuario, UsuarioDTO usuarioDTO);
    UsuarioDTO buscarUsuario(int idUsuario);
    void eliminarUsuario(int idUsuario);
    List<UsuarioDTO> obtenerUsuario();
}
