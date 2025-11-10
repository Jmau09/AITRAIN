package com.aitrain.workouts.domain.model.gateway;

import com.aitrain.workouts.domain.model.Rutina;

import java.util.List;

public interface RutinaGateway {
    Rutina guardarRutina(Rutina rutina);
    void eliminarRutinaPorId (Long id);
    Rutina buscarRutinaPorId (Long id);
    Rutina actualizarRutina (Rutina rutina);
    List<Rutina> obtenerRutinas();

}
