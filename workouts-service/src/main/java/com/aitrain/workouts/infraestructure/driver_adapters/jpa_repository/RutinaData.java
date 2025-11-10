package com.aitrain.workouts.infraestructure.driver_adapters.jpa_repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rutinas")
@Data //solo se utiliza para el tema de la base de datos

public class RutinaData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String objetivo; // volumen, definicion, resistencia
    private String nivel;    // principiante, intermedio, avanzado
    private Integer duracion; // minutos
    private String descripcion;
}
