package com.restaurante.app.controller;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Usuario;
import com.restaurante.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> ingresarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.ingresarUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UsuarioDTO> getUsuario(){return usuarioService.obtenerUsuario();}

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable(name = "id") int id)
    {
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable(name = "id") int id, @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        UsuarioDTO usuario = usuarioService.actualizarUsuario(id,usuarioDTO);
        return new ResponseEntity<>(usuario,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteUsuario(@PathVariable(name = "id") int id){
        UsuarioDTO usuario = usuarioService.buscarUsuario(id);
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>("Usuario con ID:"+id+" eliminado con éxito",HttpStatus.OK);
    }

}
