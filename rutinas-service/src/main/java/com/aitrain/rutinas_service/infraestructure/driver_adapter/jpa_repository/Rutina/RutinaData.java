package com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String nombre;
    private String objetivo;
    private int duracionSemanas; // duración total en semanas

    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    // Relación bidireccional con DiasRutina
    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaRutinaData> dias = new ArrayList<>();
}

