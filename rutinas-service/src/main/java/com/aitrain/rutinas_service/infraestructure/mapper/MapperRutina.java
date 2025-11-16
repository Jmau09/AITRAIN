package com.aitrain.rutinas_service.infraestructure.mapper;

import com.aitrain.rutinas_service.domain.model.DiaRutina;
import com.aitrain.rutinas_service.domain.model.Ejercicio;
import com.aitrain.rutinas_service.domain.model.Rutina;
import com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina.DiaRutinaData;
import com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina.EjercicioData;
import com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina.RutinaData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperRutina {

    // ==========================
    //   DOMINIO → DATA
    // ==========================
    public RutinaData toRutinaData(Rutina rutina) {
        if (rutina == null) return null;

        RutinaData rutinaData = new RutinaData();
        rutinaData.setId(rutina.getId());
        rutinaData.setUserId(rutina.getUserId());
        rutinaData.setNombre(rutina.getNombre());
        rutinaData.setObjetivo(rutina.getObjetivo());
        rutinaData.setDuracionSemanas(rutina.getDuracionSemanas());
        rutinaData.setFechaCreacion(rutina.getFechaCreacion());

        List<DiaRutinaData> diasData = new ArrayList<>();
        if (rutina.getDias() != null) {
            for (DiaRutina dia : rutina.getDias()) {
                DiaRutinaData diaData = new DiaRutinaData();
                diaData.setId(dia.getId());
                diaData.setNombreDia(dia.getNombreDia());
                diaData.setRutina(rutinaData); // clave: padre asignado

                List<EjercicioData> ejerciciosData = new ArrayList<>();
                if (dia.getEjercicios() != null) {
                    for (Ejercicio e : dia.getEjercicios()) {
                        EjercicioData ejData = new EjercicioData();
                        ejData.setId(e.getId());
                        ejData.setNombre(e.getNombre());
                        ejData.setSeries(e.getSeries());
                        ejData.setRepeticiones(e.getRepeticiones());
                        ejData.setTiempo(e.getTiempo());
                        ejData.setDiaRutina(diaData); // clave: asignar padre
                        ejerciciosData.add(ejData);
                    }
                }
                diaData.setEjercicios(ejerciciosData);
                diasData.add(diaData);
            }
        }
        rutinaData.setDias(diasData);

        return rutinaData;
    }

    // ==========================
    //   DATA → DOMINIO
    // ==========================
    public Rutina toRutina(RutinaData rutinaData) {
        if (rutinaData == null) return null;

        Rutina rutina = new Rutina();
        rutina.setId(rutinaData.getId());
        rutina.setUserId(rutinaData.getUserId());
        rutina.setNombre(rutinaData.getNombre());
        rutina.setObjetivo(rutinaData.getObjetivo());
        rutina.setDuracionSemanas(rutinaData.getDuracionSemanas());
        rutina.setFechaCreacion(rutinaData.getFechaCreacion());

        List<DiaRutina> dias = new ArrayList<>();
        if (rutinaData.getDias() != null) {
            for (DiaRutinaData diaData : rutinaData.getDias()) {
                DiaRutina dia = new DiaRutina();
                dia.setId(diaData.getId());
                dia.setNombreDia(diaData.getNombreDia());

                List<Ejercicio> ejercicios = new ArrayList<>();
                if (diaData.getEjercicios() != null) {
                    for (EjercicioData eData : diaData.getEjercicios()) {
                        Ejercicio e = new Ejercicio();
                        e.setId(eData.getId());
                        e.setNombre(eData.getNombre());
                        e.setSeries(eData.getSeries());
                        e.setRepeticiones(eData.getRepeticiones());
                        e.setTiempo(eData.getTiempo());
                        ejercicios.add(e);
                    }
                }
                dia.setEjercicios(ejercicios);
                dias.add(dia);
            }
        }
        rutina.setDias(dias);

        return rutina;
    }
}
