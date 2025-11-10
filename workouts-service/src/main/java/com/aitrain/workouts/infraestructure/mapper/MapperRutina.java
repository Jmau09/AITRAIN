package com.aitrain.workouts.infraestructure.mapper;

import com.aitrain.workouts.domain.model.Rutina;
import com.aitrain.workouts.infraestructure.driver_adapters.jpa_repository.RutinaData;
import org.springframework.stereotype.Component;

@Component
public class MapperRutina {
    public Rutina toRutina(RutinaData rutinaData) {
        return new Rutina(
                rutinaData.getId(),
                rutinaData.getNombre(),
                rutinaData.getObjetivo(),
                rutinaData.getNivel(),
                rutinaData.getDuracion(),
                rutinaData.getDescripcion()
        );
    }

    public RutinaData toRutinaData(Rutina rutina) {
        return new RutinaData(
                rutina.getId(),
                rutina.getNombre(),
                rutina.getObjetivo(),
                rutina.getNivel(),
                rutina.getDuracion(),
                rutina.getDescripcion()
        );
    }
}
