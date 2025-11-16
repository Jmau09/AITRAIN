package com.aitrain.rutinas_service.domain.model.gateway;

import com.aitrain.rutinas_service.domain.model.Rutina;
import java.util.List;

public interface RutinaGateway {

    Rutina save(Rutina rutina);                       // crear o actualizar
    Rutina findById(Long id);                         // buscar por id (si no existe, lanza excepción en infra)
    List<Rutina> findByUserId(String userId);        // buscar todas las rutinas de un usuario
    void deleteById(Long id);
}
