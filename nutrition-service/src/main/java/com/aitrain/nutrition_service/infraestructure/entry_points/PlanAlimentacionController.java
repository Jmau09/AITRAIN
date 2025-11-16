package com.aitrain.nutrition_service.infraestructure.entry_points;

import com.aitrain.nutrition_service.domain.model.PlanAlimentacion;
import com.aitrain.nutrition_service.domain.usecase.PlanAlimentacionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aitrain/nutricion")
@RequiredArgsConstructor
public class PlanAlimentacionController {

    private final PlanAlimentacionUseCase planUseCase;

    // Crear un plan
    @PostMapping("/save")
    public ResponseEntity<PlanAlimentacion> createPlan(@RequestBody PlanAlimentacion plan) {
        PlanAlimentacion creado = planUseCase.createPlan(plan);
        return ResponseEntity.ok(creado);
    }

    // Obtener plan por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlanAlimentacion> getPlanById(@PathVariable Long id) {
        PlanAlimentacion plan = planUseCase.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    // Obtener planes de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PlanAlimentacion>> getPlansByUserId(@PathVariable String userId) {
        List<PlanAlimentacion> planes = planUseCase.getPlansByUserId(userId);
        return ResponseEntity.ok(planes);
    }

    // Eliminar plan
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        planUseCase.deletePlan(id);
        return ResponseEntity.ok("Plan eliminado correctamente");
    }
}