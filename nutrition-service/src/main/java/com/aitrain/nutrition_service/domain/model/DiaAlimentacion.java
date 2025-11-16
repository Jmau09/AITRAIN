package com.aitrain.nutrition_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiaAlimentacion {
    private Long id;
    private String nombreDia;        // Lunes, Martes, etc.
    private List<Comida> comidas;
}