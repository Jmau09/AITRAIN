package com.aitrain.users.infraestructure.DTO;

import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion.ValoracionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaValoracion {
    private String mensaje;
    private ValoracionData valoracion;
}