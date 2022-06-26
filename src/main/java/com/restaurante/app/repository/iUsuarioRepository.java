package com.restaurante.app.repository;

import com.restaurante.app.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface iUsuarioRepository extends CrudRepository<Usuario,Integer> {
    public Optional<Usuario> findByNombre(String nombre);
}
