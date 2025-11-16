package com.aitrain.rutinas_service.domain.usecase;

import com.aitrain.rutinas_service.domain.exceptions.RutinaInvalidDataException;
import com.aitrain.rutinas_service.domain.exceptions.RutinaNotFoundException;
import com.aitrain.rutinas_service.domain.model.DiaRutina;
import com.aitrain.rutinas_service.domain.model.Ejercicio;
import com.aitrain.rutinas_service.domain.model.Rutina;
import com.aitrain.rutinas_service.domain.model.gateway.RutinaGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class RutinaUseCase {

    private final RutinaGateway rutinaGateway;

    public Rutina createRutina(Rutina rutina) {
        if (rutina.getUserId() == null || rutina.getUserId().trim().isEmpty()) {
            throw new RutinaInvalidDataException("El email del usuario es necesario");
        }
        if (rutina.getNombre() == null || rutina.getNombre().trim().isEmpty()) {
            throw new RutinaInvalidDataException("El nombre del usuario es necesario");
        }
        if (rutina.getObjetivo() == null || rutina.getObjetivo().trim().isEmpty()) {
            throw new RutinaInvalidDataException("El objetivo del usuario es necesario");
        }
        if (rutina.getDuracionSemanas() <= 0 ) {
            throw new RutinaInvalidDataException("Ingrese una duración valida");
        }
        if (rutina.getFechaCreacion() == null) {
            rutina.setFechaCreacion(LocalDate.now());
        }
        if (rutina.getDias() == null || rutina.getDias().isEmpty()) {
            throw new RutinaInvalidDataException("La rutina debe contener al menos un día.");
        }
        for (DiaRutina dia : rutina.getDias()) {

            if (dia.getNombreDia() == null || dia.getNombreDia().trim().isEmpty()) {
                throw new RutinaInvalidDataException("Cada día debe tener un nombre.");
            }

            if (dia.getEjercicios() == null || dia.getEjercicios().isEmpty()) {
                throw new RutinaInvalidDataException("Cada día debe contener al menos un ejercicio.");
            }

            // ➜ Validación interna de cada ejercicio
            for (Ejercicio ej : dia.getEjercicios()) {

                if (ej.getNombre() == null || ej.getNombre().trim().isEmpty()) {
                    throw new RutinaInvalidDataException("Cada ejercicio debe tener un nombre.");
                }

                if (ej.getSeries() <= 0) {
                    throw new RutinaInvalidDataException("Las series deben ser mayores a 0.");
                }

                if (ej.getRepeticiones() <= 0) {
                    throw new RutinaInvalidDataException("Las repeticiones deben ser mayores a 0.");
                }
            }
        }
        return rutinaGateway.save(rutina);
    }

    public Rutina getRutinaById(Long id) {
        if (id == null || id.intValue() == 0) {
            throw new RutinaInvalidDataException("El ID de la rutina es inválido");
        }
        return rutinaGateway.findById(id);
    }

    public List<Rutina> getRutinasByUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new RutinaInvalidDataException("El ID del usuario no puede ser vacío");
        }
        return rutinaGateway.findByUserId(userId);
    }

    public void deleteRutina(Long id) {
        if (id == null || id.intValue() == 0) {
            throw new RutinaInvalidDataException("El ID de la rutina no es válido");
        }
        Rutina existente = rutinaGateway.findById(id);
        if (existente == null) {
            throw new RutinaNotFoundException("No existe la rutina con id: " + id);
        }
        rutinaGateway.deleteById(id);
    }
}
