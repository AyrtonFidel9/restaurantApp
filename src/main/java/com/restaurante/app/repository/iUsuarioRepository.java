package com.restaurante.app.repository;

import com.restaurante.app.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface iUsuarioRepository extends CrudRepository<Usuario,Integer> {
}
