package com.aitrain.rutinas_service.domain.model;

import com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina.RutinaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter
public class DiaRutina {
    private Long id;
    private String nombreDia; // Lunes, Martes, etc.
    private Rutina rutina;
    private List<Ejercicio> ejercicios;
}
