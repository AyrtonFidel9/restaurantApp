package com.restaurante.app.repository;

import com.restaurante.app.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface iUsuarioRepository extends CrudRepository<Usuario,Integer> {
    public Optional<Usuario> findByNombre(String nombre);
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByNombreOrEmail(String nombre, String email);

    public Boolean existsByNombre(String nombre);

    public Boolean existsByEmail(String email);

    public Boolean existsByCedulaOrEmail(String cedula, String email);

    @Query(value = "SELECT COUNT(*) FROM usuarios WHERE id <> :id AND "
            + "(email = :email OR cedula = :cedula )", nativeQuery = true)
    int verificarEmailCedula(int id, String email, String cedula);
}
