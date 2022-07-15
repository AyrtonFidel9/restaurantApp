package com.restaurante.app.configuration;

import com.restaurante.app.seguridad.CustomUserService;
import com.restaurante.app.seguridad.JwtAuthenticationEntryPoint;
import com.restaurante.app.seguridad.JwtAuthenticationFilter;
import com.restaurante.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

     @Bean
     public JwtAuthenticationFilter jwtAuthenticationFilter()
     {
         return new JwtAuthenticationFilter();
     }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/").permitAll()
                .antMatchers("/api/auth/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/reservas").hasRole("CLIENTE")
                .antMatchers(HttpMethod.DELETE, "/api/reservas").hasRole("CLIENTE")
                .antMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/api/alimentos").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.PUT, "/api/alimentos").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.DELETE, "/api/alimentos").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.POST,"/api/menus").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.PUT,"/api/menus").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.DELETE,"/api/menus").hasAnyRole("ADMIN", "MESERO")
                .antMatchers(HttpMethod.POST,"api/mesas").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "api/mesas").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "api/mesas").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "api/pedidos").hasRole("MESERO")
                .antMatchers(HttpMethod.POST, "api/pedidos").hasRole("MESERO")
                .antMatchers(HttpMethod.DELETE, "api/pedidos").hasRole("MESERO")
                .antMatchers(HttpMethod.PUT, "api/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "api/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "api/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "api/venta").hasRole("CAJERO")
                .antMatchers(HttpMethod.POST, "api/venta").hasRole("CAJERO")
                .antMatchers(HttpMethod.DELETE, "api/venta").hasRole("CAJERO")
                    //.authenticated()
        ;
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
