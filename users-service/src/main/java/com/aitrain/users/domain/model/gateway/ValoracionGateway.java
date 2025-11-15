package com.aitrain.users.domain.model.gateway;

import com.aitrain.users.domain.model.Valoracion;


public interface ValoracionGateway {
    Valoracion guardarValoracion(Valoracion valoracion);
    Valoracion buscarPorEmailUsuario(String emailUsuario);
    Valoracion actualizarValoracion(Valoracion valoracion);
    void eliminarValoracion(String emailUsuario);


}