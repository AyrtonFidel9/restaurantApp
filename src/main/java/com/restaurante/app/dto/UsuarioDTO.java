package com.restaurante.app.dto;

import com.restaurante.app.entity.Rol;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioDTO {

    private int idUsuario;
    @NotNull(message = "El restaurante es requerido")
    //@Min(value=1)
    private int idRestaurante;
    @NotNull(message = "Debe ingresar el nombre del usuario")
    @NotBlank(message = "Se necesita el nombre del usuario")
    @NotEmpty
    private String nombre;
    @NotNull(message = "Debe ingresar el apellido del usuario")
    @NotBlank(message = "Se necesita el apellido del usuario")
    @NotEmpty
    private String apellido;
    @NotNull(message = "Debe ingresar la cedula del usuario")
    @NotBlank(message = "Se necesita la cedula del usuario")
    @NotEmpty
    private String cedula;
    @NotNull(message = "Debe ingresar el correo del usuario")
    @NotBlank(message = "Se necesita el correo del usuario")
    @NotEmpty
    private String email;
    @NotNull(message = "Debe ingresar la clave")
    @NotBlank(message = "Se necesita la clave")
    @NotEmpty
    private String password;
    @NotNull(message = "Debe ingresar el rol")
    private Rol rol;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
