package com.aitrain.users.domain.usecase;

import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;

import java.util.Optional;

public class ValoracionUseCase {
    private final ValoracionGateway valoracionGateway;

    public ValoracionUseCase(ValoracionGateway gateway) {
        this.valoracionGateway = gateway;
    }

    public Valoracion crearValoracion(String emailUsuario, Valoracion valoracion) {
        valoracion.setEmailUsuario(emailUsuario);
        return valoracionGateway.guardarValoracion(valoracion);
    }

    public Valoracion obtenerValoracion(Long id) {
        return valoracionGateway.buscarPorId(id);
    }

    public void eliminarValoracion(Long id) {
        valoracionGateway.eliminarValoracion(id);
    }
}