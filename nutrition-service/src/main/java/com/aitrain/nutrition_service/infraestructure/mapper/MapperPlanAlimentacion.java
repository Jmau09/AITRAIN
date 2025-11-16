package com.aitrain.nutrition_service.infraestructure.mapper;

import com.aitrain.nutrition_service.domain.model.Comida;
import com.aitrain.nutrition_service.domain.model.DiaAlimentacion;
import com.aitrain.nutrition_service.domain.model.PlanAlimentacion;
import com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion.ComidaData;
import com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion.DiaAlimentacionData;
import com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion.PlanAlimentacionData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperPlanAlimentacion {

    // DOMINIO → DATA
    public PlanAlimentacionData toPlanData(PlanAlimentacion plan) {
        if (plan == null) return null;

        PlanAlimentacionData planData = new PlanAlimentacionData();
        planData.setId(plan.getId());
        planData.setUserId(plan.getUserId());
        planData.setNombre(plan.getNombre());
        planData.setObjetivo(plan.getObjetivo());
        planData.setFechaCreacion(plan.getFechaCreacion());

        if (plan.getDias() != null && !plan.getDias().isEmpty()) {
            List<DiaAlimentacionData> diasData = plan.getDias().stream()
                    .map(dia -> {
                        DiaAlimentacionData diaData = new DiaAlimentacionData();
                        diaData.setId(dia.getId());
                        diaData.setNombreDia(dia.getNombreDia());
                        diaData.setPlan(planData);

                        if (dia.getComidas() != null && !dia.getComidas().isEmpty()) {
                            List<ComidaData> comidasData = dia.getComidas().stream()
                                    .map(c -> {
                                        ComidaData cData = new ComidaData();
                                        cData.setId(c.getId());
                                        cData.setNombre(c.getNombre());
                                        cData.setDescripcion(c.getDescripcion());
                                        cData.setCalorias(c.getCalorias());
                                        cData.setDia(diaData);
                                        return cData;
                                    }).collect(Collectors.toList());
                            diaData.setComidas(comidasData);
                        } else {
                            diaData.setComidas(new ArrayList<>());
                        }

                        return diaData;
                    }).collect(Collectors.toList());
            planData.setDias(diasData);
        } else {
            planData.setDias(new ArrayList<>());
        }

        return planData;
    }

    // DATA → DOMINIO
    public PlanAlimentacion toPlan(PlanAlimentacionData planData) {
        if (planData == null) return null;

        PlanAlimentacion plan = new PlanAlimentacion();
        plan.setId(planData.getId());
        plan.setUserId(planData.getUserId());
        plan.setNombre(planData.getNombre());
        plan.setObjetivo(planData.getObjetivo());
        plan.setFechaCreacion(planData.getFechaCreacion());

        if (planData.getDias() != null && !planData.getDias().isEmpty()) {
            List<DiaAlimentacion> dias = planData.getDias().stream()
                    .map(diaData -> {
                        DiaAlimentacion dia = new DiaAlimentacion();
                        dia.setId(diaData.getId());
                        dia.setNombreDia(diaData.getNombreDia());

                        if (diaData.getComidas() != null && !diaData.getComidas().isEmpty()) {
                            List<Comida> comidas = diaData.getComidas().stream()
                                    .map(cData -> {
                                        Comida c = new Comida();
                                        c.setId(cData.getId());
                                        c.setNombre(cData.getNombre());
                                        c.setDescripcion(cData.getDescripcion());
                                        c.setCalorias(cData.getCalorias());
                                        return c;
                                    }).collect(Collectors.toList());
                            dia.setComidas(comidas);
                        } else {
                            dia.setComidas(new ArrayList<>());
                        }

                        return dia;
                    }).collect(Collectors.toList());
            plan.setDias(dias);
        } else {
            plan.setDias(new ArrayList<>());
        }

        return plan;
    }
}