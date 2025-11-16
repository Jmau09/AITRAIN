package com.aitrain.nutrition_service.domain.usecase;

import com.aitrain.nutrition_service.domain.exceptions.PlanAlimentacionInvalidDataException;
import com.aitrain.nutrition_service.domain.exceptions.PlanAlimentacionNotFoundException;
import com.aitrain.nutrition_service.domain.model.PlanAlimentacion;
import com.aitrain.nutrition_service.domain.model.gateway.PlanAlimentacionGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class PlanAlimentacionUseCase {
    private final PlanAlimentacionGateway planGateway;

    public PlanAlimentacion createPlan(PlanAlimentacion plan) {
        // Validaciones
        if (plan.getUserId() == null || plan.getUserId().trim().isEmpty()) {
            throw new PlanAlimentacionInvalidDataException("El ID del usuario es obligatorio.");
        }

        if (plan.getNombre() == null || plan.getNombre().trim().isEmpty()) {
            throw new PlanAlimentacionInvalidDataException("El nombre del plan es obligatorio.");
        }

        if (plan.getObjetivo() == null || plan.getObjetivo().trim().isEmpty()) {
            throw new PlanAlimentacionInvalidDataException("El objetivo del plan es obligatorio.");
        }

        if (plan.getFechaCreacion() == null) {
            plan.setFechaCreacion(LocalDate.now());
        }

        return planGateway.save(plan);
    }

    public PlanAlimentacion getPlanById(Long id) {
        PlanAlimentacion plan = planGateway.findById(id);
        if (plan == null) {
            throw new PlanAlimentacionNotFoundException("No se encontró un plan con ID: " + id);
        }
        return plan;
    }

    public List<PlanAlimentacion> getPlansByUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new PlanAlimentacionInvalidDataException("El ID del usuario es obligatorio.");
        }
        return planGateway.findByUserId(userId);
    }

    public void deletePlan(Long id) {
        PlanAlimentacion plan = planGateway.findById(id);
        if (plan == null) {
            throw new PlanAlimentacionNotFoundException("No se encontró un plan con ID: " + id);
        }
        planGateway.deleteById(id);
    }
}
