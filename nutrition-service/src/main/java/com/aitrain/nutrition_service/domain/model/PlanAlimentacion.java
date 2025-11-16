package com.aitrain.nutrition_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlanAlimentacion {
    private Long id;
    private String userId;           // ID o correo del usuario
    private String nombre;           // Nombre del plan (ej: "Plan de Masa Muscular")
    private String objetivo;         // Objetivo del plan (ej: "Ganar masa")
    private LocalDate fechaCreacion;
    private List<DiaAlimentacion> dias;
}
