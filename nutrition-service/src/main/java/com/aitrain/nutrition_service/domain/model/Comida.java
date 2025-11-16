package com.aitrain.nutrition_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Comida {
    private Long id;
    private String nombre;           // Ej: "Desayuno", "Almuerzo"
    private String descripcion;      // Ej: "2 huevos, 1 taza de avena"
    private Integer calorias;
}