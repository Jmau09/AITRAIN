package com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dias_rutina")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaRutinaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDia;

    // Cada día pertenece a una rutina
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_id", nullable = false)
    private RutinaData rutina;

    // Lista de ejercicios de ese día
    @OneToMany(mappedBy = "diaRutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EjercicioData> ejercicios = new ArrayList<>();
}

