package com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "dias_alimentacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaAlimentacionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDia;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlanAlimentacionData plan;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dia_id")
    private List<ComidaData> comidas;
}