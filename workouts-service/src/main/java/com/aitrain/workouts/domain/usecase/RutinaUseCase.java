package com.aitrain.workouts.domain.usecase;

import com.aitrain.workouts.domain.model.Rutina;
import com.aitrain.workouts.domain.model.gateway.RutinaGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RutinaUseCase {

    private final RutinaGateway rutinaGateway;

    public String guardarRutina(Rutina rutina){
        if (rutina.getNombre() == null || rutina.getNombre().trim().isEmpty()) {
            return "El campo nombre es obligatorio";
        }
        if (rutina.getObjetivo() == null || rutina.getObjetivo().trim().isEmpty()) {
            return "Debe especificar un objetivo de entrenamiento";
        }

        rutinaGateway.guardarRutina(rutina);
        return "Rutina guardada correctamente";
        }

        public Rutina buscarRutinaPorId(Long id){
        try {
            Rutina rutina = rutinaGateway.buscarRutinaPorId(id);
            return rutina;
        } catch (Exception e){
            System.out.println("Error al buscar el Rutina" + e.getMessage());
            return null;
        }
    }

        public void  eliminarRutinaPorId(Long id){
        try {
            rutinaGateway.eliminarRutinaPorId(id);
        } catch (Exception e){
            System.out.println("Error al eliminar la rutina" + e.getMessage());
        }
    }

        public Rutina actualizarRutina(Rutina rutina){
        if (rutina.getId() == null) {
            throw new IllegalArgumentException("El id es obligatorio para actualizar");
        }
        Rutina rutinaExistente = rutinaGateway.buscarRutinaPorId(rutina.getId());
        if (rutinaExistente == null) {
            return null;
            }
        return  rutinaGateway.actualizarRutina(rutinaExistente);
        }

    public List<Rutina> obtenerRutinas() {
        return rutinaGateway.obtenerRutinas();
    }




}

