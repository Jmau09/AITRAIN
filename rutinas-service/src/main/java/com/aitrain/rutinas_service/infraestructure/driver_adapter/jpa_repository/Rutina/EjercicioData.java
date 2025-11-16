package com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ejercicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjercicioData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;         // Nombre del ejercicio
    private Integer series;        // número de series
    private Integer repeticiones;  // número de repeticiones
    private String tiempo;         // tiempo si aplica (ej: "45 segundos")

    // Cada ejercicio pertenece a un día
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dia_id", nullable = false)
    private DiaRutinaData diaRutina;
}
