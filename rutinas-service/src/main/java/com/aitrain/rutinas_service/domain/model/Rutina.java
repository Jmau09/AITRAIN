package com.aitrain.rutinas_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter
public class Rutina {
    private Long id;
    private String userId;       // correo del usuario (identificador único)
    private String nombre;       // nombre de la rutina (ej: Full Body Principiante)
    private String objetivo;     // objetivo principal (ej: Bajar de peso, Ganar masa)
    private int duracionSemanas; // duración total en semanas
    private LocalDate fechaCreacion;
    private List<DiaRutina> dias;


}
