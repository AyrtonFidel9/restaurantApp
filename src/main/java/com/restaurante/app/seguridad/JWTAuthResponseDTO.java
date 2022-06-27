package com.restaurante.app.seguridad;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class JWTAuthResponseDTO {
    @NonNull
    private String tokenAcceso;
    private String tipoToken = "Bearer";
}
