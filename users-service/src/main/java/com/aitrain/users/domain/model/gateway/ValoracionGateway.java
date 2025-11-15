package com.aitrain.users.domain.model.gateway;

import com.aitrain.users.domain.model.Valoracion;


public interface ValoracionGateway {
    Valoracion guardarValoracion(Valoracion valoracion);

    Valoracion buscarPorId(Long id);

    void eliminarValoracion(Long id);


}