package com.aitrain.progress_service.infraestructure.driver_adapters.jpa_repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progreso")
@Data //solo se utiliza para el tema de la base de datos
public class ProgresoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cedulaUsuario;

    private Double peso;
    private Double altura;
    private Double imc;
    private Double grasaCorporal;
    private LocalDate fechaRegistro;
}
