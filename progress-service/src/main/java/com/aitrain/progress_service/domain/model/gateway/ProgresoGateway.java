package com.aitrain.progress_service.domain.model.gateway;

import com.aitrain.progress_service.domain.model.Progreso;

import java.util.List;

public interface ProgresoGateway {
    Progreso guardarProgreso(Progreso progreso);
    Progreso obtenerPorId(Long id);
    List<Progreso> obtenerTodos();
    void eliminarPorId(Long id);
}
