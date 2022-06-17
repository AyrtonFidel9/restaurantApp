package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Pedido;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.Usuario;
import com.restaurante.app.mapper.iUsuarioMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import com.restaurante.app.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Usuario> usuarioResult = usuarioRepository.findById(idUsuario);
        return mapper.toUsuarioDTO(usuarioResult
                .orElseThrow(()->
                        new RuntimeException("Usuario no encontrado")));
    }

    @Override
    public UsuarioDTO actualizarUsuario(int idUsuario,UsuarioDTO usuarioDTO)
    {
        Usuario usuarioex = mapper.toUsuario(buscarUsuario(idUsuario)); //comprueba si existe el usuario
        usuarioex.setNombre(usuarioDTO.getNombre());
        usuarioex.setApellido(usuarioDTO.getApellido());
        usuarioex.setCedula(usuarioDTO.getCedula());
        usuarioex.setEmail(usuarioDTO.getEmail());
        usuarioex.setPassword(usuarioDTO.getPassword());
        usuarioex.setRol(usuarioDTO.getRol());
        usuarioRepository.save(usuarioex);
        return mapper.toUsuarioDTO(usuarioex);
    }
    @Override
    public void eliminarUsuario(int idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public List<UsuarioDTO> obtenerUsuario() {
        return mapper.toUsuarioDTO((List<Usuario>)usuarioRepository.findAll());
    }

}
