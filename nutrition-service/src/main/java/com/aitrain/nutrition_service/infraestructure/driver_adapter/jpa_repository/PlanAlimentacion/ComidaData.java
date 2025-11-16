package com.aitrain.nutrition_service.infraestructure.driver_adapter.jpa_repository.PlanAlimentacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comidas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComidaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Integer calorias;

    @ManyToOne
    @JoinColumn(name = "dia_id")
    private DiaAlimentacionData dia;
}