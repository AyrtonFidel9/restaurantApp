package com.restaurante.app.exceptions;

import org.springframework.http.HttpStatus;

public class RestauranteAppException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public RestauranteAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public RestauranteAppException(HttpStatus estado, String mensaje, String mensajeAlt) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensajeAlt;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
