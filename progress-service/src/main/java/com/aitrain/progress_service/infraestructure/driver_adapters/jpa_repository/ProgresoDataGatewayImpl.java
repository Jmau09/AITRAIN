package com.aitrain.progress_service.infraestructure.driver_adapters.jpa_repository;


import com.aitrain.progress_service.domain.model.Progreso;
import com.aitrain.progress_service.domain.model.gateway.ProgresoGateway;
import com.aitrain.progress_service.infraestructure.mapper.MapperProgreso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProgresoDataGatewayImpl implements ProgresoGateway {

    private final MapperProgreso mapperProgreso;
    private final ProgresoDataJpaRepository repository;
    @Override
    public Progreso guardarProgreso(Progreso progreso) {
    ProgresoData progresoData = mapperProgreso.toProgresoData(progreso);
    return mapperProgreso.toProgreso(repository.save(progresoData));
    }

    @Override
    public Progreso obtenerPorId(Long id) {
        return repository.findById(id)
                .map(progresoData -> mapperProgreso.toProgreso(progresoData))
                .orElse(null);
    }

    @Override
    public List<Progreso> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapperProgreso::toProgreso)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarPorId(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }
}
