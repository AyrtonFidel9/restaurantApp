package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;

import java.util.List;

public interface iUsuarioService {
    UsuarioDTO ingresarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO buscarUsuario(int idUsuario);
    void eliminarUsuario(int idUsuario);
    List<UsuarioDTO> obtenerUsuario();
}
