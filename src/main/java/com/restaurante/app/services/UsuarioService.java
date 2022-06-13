package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.Usuario;
import com.restaurante.app.mapper.iUsuarioMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import com.restaurante.app.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements iUsuarioService{

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iUsuarioMapper mapper;

    @Override
    public UsuarioDTO ingresarUsuario(UsuarioDTO usuarioDTO)
    {
        usuarioDTO.setIdRestaurante(1);
        int idRes = usuarioDTO.getIdRestaurante();
        Usuario usuario = mapper.toUsuario(usuarioDTO);

        Restaurante res = restauranteRepository.findById(idRes).orElseThrow(()->new RuntimeException("Restaurante no encontrado"));
        usuario.setRestaurante(res);

        Usuario ingUsuario = usuarioRepository.save(usuario);
        return mapper.toUsuarioDTO(ingUsuario);
    }

    @Override
    public UsuarioDTO buscarUsuario(int idUsuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(int idUsuario) {

    }

    @Override
    public List<UsuarioDTO> obtenerUsuario() {
        return mapper.toUsuarioDTO((List<Usuario>)usuarioRepository.findAll());
    }

}
