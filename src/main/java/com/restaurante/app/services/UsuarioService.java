package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.entity.Pedido;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.Rol;
import com.restaurante.app.entity.Usuario;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.mapper.iUsuarioMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import com.restaurante.app.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements iUsuarioService{

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iUsuarioMapper mapper;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UsuarioDTO ingresarUsuario(UsuarioDTO usuarioDTO)
    {
        usuarioDTO.setIdRestaurante(1);
        int idRes = usuarioDTO.getIdRestaurante();
        usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        Usuario usuario = mapper.toUsuario(usuarioDTO);

        Restaurante res = restauranteRepository.findById(idRes).orElseThrow(()->
                new ResourceNotFoundException("Restaurante","id",idRes));
        usuario.setRestaurante(res);

        if(validadorDeCedula(usuario.getCedula())) {
            Usuario ingUsuario = usuarioRepository.save(usuario);
            return mapper.toUsuarioDTO(ingUsuario);
        }
        else
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,"Cedula no valida");
    }

    static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepción ocurrió en el proceso de validación");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

    @Override
    public UsuarioDTO buscarUsuario(int idUsuario) {
        Optional<Usuario> usuarioResult = usuarioRepository.findById(idUsuario);
        return mapper.toUsuarioDTO(usuarioResult
                .orElseThrow(()->
                        new ResourceNotFoundException("Usuario","id",idUsuario)));
    }

    @Override
    public UsuarioDTO actualizarUsuario(int idUsuario,UsuarioDTO usuarioDTO)
    {
        if(usuarioRepository.existsById(idUsuario))
        {
            usuarioDTO.setIdUsuario(idUsuario);
            return ingresarUsuario(usuarioDTO);
        }
        else{
            throw new ResourceNotFoundException("Usuario","id",idUsuario);
        }
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
