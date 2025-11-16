package com.aitrain.rutinas_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter
public class Ejercicio {
    private Long id;
    private String nombre;     // Sentadillas, Plancha, etc.
    private Integer series;    // número de series (opcional si es tiempo)
    private Integer repeticiones; // número de reps por serie (opcional si es tiempo)
    private String tiempo;
    private DiaRutina diaRutina;
}
