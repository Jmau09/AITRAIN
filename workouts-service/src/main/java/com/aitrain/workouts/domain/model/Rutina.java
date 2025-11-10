package com.aitrain.workouts.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter
public class Rutina {
    private Long id;
    private String nombre;
    private String objetivo; // volumen, definicion, resistencia
    private String nivel;    // principiante, intermedio, avanzado
    private Integer duracion; // minutos
    private String descripcion;
}
