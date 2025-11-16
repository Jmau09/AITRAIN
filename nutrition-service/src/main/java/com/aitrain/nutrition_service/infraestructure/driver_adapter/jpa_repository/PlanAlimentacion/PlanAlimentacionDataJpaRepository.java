package com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanAlimentacionDataJpaRepository extends JpaRepository<PlanAlimentacionData, Long> {
    List<PlanAlimentacionData> findByUserId(String userId);
}
