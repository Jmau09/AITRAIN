package com.aitrain.users.domain.model.gateway;


import com.aitrain.users.domain.model.Notificacion;

public interface NotificacionGateway {
    void enviarMensaje(Notificacion mensajeJson);
}
