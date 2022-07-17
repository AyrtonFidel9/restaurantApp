package com.restaurante.app.seguridad;

import com.restaurante.app.entity.Rol;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class JWTAuthResponseDTO {
    @NonNull
    private String tokenAcceso;
    private String tipoToken = "Bearer";
    private int id;
    private Rol rol;
}
