package com.restaurante.app.seguridad;

import com.restaurante.app.entity.Usuario;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    iUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(username).orElseThrow(()-> new RuntimeException(
               "usuario no encontrado"));
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().name()));
        System.out.println("roles = " + roles);
        UserDetails userD = new User(usuario.getNombre(),usuario.getPassword(), roles );
        return userD;
    }
}
