package com.aitrain.workouts.infraestructure.driver_adapters.jpa_repository;

import com.aitrain.workouts.domain.model.Rutina;
import com.aitrain.workouts.domain.model.gateway.RutinaGateway;
import com.aitrain.workouts.infraestructure.mapper.MapperRutina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RutinaDataGatewayImpl implements RutinaGateway {

    private final MapperRutina mapperRutina;
    private  final RutinaDataJpaRepository repository;
    @Override
    public Rutina guardarRutina(Rutina rutina) {
        RutinaData rutinaData = mapperRutina.toRutinaData(rutina);
        return mapperRutina.toRutina(repository.save(rutinaData));
    }

    @Override
    public void eliminarRutinaPorId(Long id) {

        try{
            repository.deleteById(id);
        } catch (Exception error) {
        throw new RuntimeException(error.getMessage());
        }
    }

    @Override
    public Rutina buscarRutinaPorId(Long id) {
        return repository.findById(id)
                .map(rutinaData -> mapperRutina.toRutina(rutinaData))
                .orElse(null);
    }

    @Override
    public Rutina actualizarRutina(Rutina rutina) {
        RutinaData rutinaData = mapperRutina.toRutinaData(rutina);

        if (!repository.existsById(rutina.getId())) {
            throw new RuntimeException("Usuario con Id " + rutina.getId() + "no existe");
        }
        return mapperRutina.toRutina(repository.save(rutinaData));
    }

    @Override
    public List<Rutina> obtenerRutinas() {
        return repository.findAll().stream()
                .map(mapperRutina::toRutina)
                .collect(Collectors.toList());
    }
}
