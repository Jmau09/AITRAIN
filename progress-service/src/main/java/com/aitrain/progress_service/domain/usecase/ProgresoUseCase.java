package com.aitrain.progress_service.domain.usecase;


import com.aitrain.progress_service.domain.model.Progreso;
import com.aitrain.progress_service.domain.model.gateway.ProgresoGateway;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ProgresoUseCase {
    private final ProgresoGateway progresoGateway;

    public String guardarProgreso(Progreso progreso) {
        if (progreso.getCedulaUsuario() == null || progreso.getCedulaUsuario().trim().isEmpty()) {
            return "La cédula del usuario es obligatoria";
        }
        if (progreso.getPeso() == null || progreso.getPeso() <= 0) {
            return "El peso debe ser mayor a 0";
        }
        if (progreso.getAltura() == null || progreso.getAltura() <= 0) {
            return "La altura debe ser mayor a 0";
        }

        // calcular IMC automáticamente
        progreso.setImc(progreso.getPeso() / Math.pow(progreso.getAltura(), 2));

        progresoGateway.guardarProgreso(progreso);
        return "Progreso guardado correctamente";
    }

    public Progreso obtenerPorId(Long id) {
        return progresoGateway.obtenerPorId(id);
    }

    public List<Progreso> obtenerTodos() {
        return progresoGateway.obtenerTodos();
    }

    public String eliminarPorId(Long id) {
        progresoGateway.eliminarPorId(id);
        return "Progreso eliminado correctamente";
    }
}
