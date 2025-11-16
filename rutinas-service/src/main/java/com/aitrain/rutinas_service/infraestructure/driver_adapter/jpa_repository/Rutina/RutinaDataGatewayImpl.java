package com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina;

import com.aitrain.rutinas_service.domain.model.Rutina;
import com.aitrain.rutinas_service.domain.model.gateway.RutinaGateway;
import com.aitrain.rutinas_service.infraestructure.mapper.MapperRutina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RutinaDataGatewayImpl implements RutinaGateway {

    private final RutinaDataJpaRepository repository;
    private final MapperRutina mapperRutina;

    @Override
    public Rutina save(Rutina rutina) {
        RutinaData rutinaData = mapperRutina.toRutinaData(rutina);
        return mapperRutina.toRutina(repository.save(rutinaData));
    }

    @Override
    public Rutina findById(Long id) {
        return repository.findById(id)
                .map(mapperRutina::toRutina)
                .orElse(null);
    }

    @Override
    public List<Rutina> findByUserId(String userId) {
        return repository.findByUserId(userId).stream()
                .map(mapperRutina::toRutina)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
