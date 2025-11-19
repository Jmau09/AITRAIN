package com.aitrain.users.infraestructure.message_broker.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoNotificacionDTO {
    private String tipo;
    private String email;
    private String telefono;
    private String mensaje;
}
