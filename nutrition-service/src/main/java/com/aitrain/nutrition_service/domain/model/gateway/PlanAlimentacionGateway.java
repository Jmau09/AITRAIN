package com.aitrain.nutrition_service.domain.model.gateway;

import com.aitrain.nutrition_service.domain.model.PlanAlimentacion;
import java.util.List;

public interface PlanAlimentacionGateway {
    PlanAlimentacion save(PlanAlimentacion plan);
    PlanAlimentacion findById(Long id);
    List<PlanAlimentacion> findByUserId(String userId);
    void deleteById(Long id);
}