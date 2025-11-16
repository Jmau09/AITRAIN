package com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion;

import com.aitrain.nutrition_service.domain.model.PlanAlimentacion;
import com.aitrain.nutrition_service.domain.model.gateway.PlanAlimentacionGateway;
import com.aitrain.nutrition_service.infraestructure.mapper.MapperPlanAlimentacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlanAlimentacionDataGatewayImpl implements PlanAlimentacionGateway {

    private final PlanAlimentacionDataJpaRepository repository;
    private final MapperPlanAlimentacion mapper;
    @Override
    public PlanAlimentacion save(PlanAlimentacion plan) {
        PlanAlimentacionData data = mapper.toPlanData(plan);
        PlanAlimentacionData saved = repository.save(data);
        return mapper.toPlan(saved);
    }

    @Override
    public PlanAlimentacion findById(Long id) {
        return repository.findById(id)
                .map(mapper::toPlan)
                .orElse(null);
    }

    @Override
    public List<PlanAlimentacion> findByUserId(String userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toPlan)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
