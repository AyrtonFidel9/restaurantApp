package com.restaurante.app.controller;

import com.restaurante.app.dto.LoginDTO;
import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Rol;
import com.restaurante.app.repository.iUsuarioRepository;
import com.restaurante.app.seguridad.JWTAuthResponseDTO;
import com.restaurante.app.seguridad.JwtTokenProvider;
import com.restaurante.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO)
    {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginDTO.getUsernameorEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UsuarioDTO usuario = usuarioService.buscarNombreOrEmail(loginDTO.getUsernameorEmail());

        int id = usuario.getIdUsuario();
        Rol rol = usuario.getRol();

        String token = jwtTokenProvider.generarToken(authentication);
        return ResponseEntity.ok((new JWTAuthResponseDTO(token,"Bearer",id,rol)));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.ingresarUsuario(usuarioDTO), HttpStatus.CREATED);
    }

}
