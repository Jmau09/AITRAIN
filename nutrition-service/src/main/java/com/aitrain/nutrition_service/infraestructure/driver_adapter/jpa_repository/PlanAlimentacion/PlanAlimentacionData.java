package com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "planes_alimentacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanAlimentacionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String nombre;
    private String objetivo;
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaAlimentacionData> dias;
}

