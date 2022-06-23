package com.restaurante.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenClave {
    public static void main(String[] args)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rpassw = "123";
        String codpassw = encoder.encode(rpassw);
        System.out.println("codpassw = " + codpassw);
    }
}
