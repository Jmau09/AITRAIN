package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "valoracion")
@Data

public class ValoracionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double pesoKg;

    @Column(nullable = false)
    private Double estaturaCm;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Double imc;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private String nivelActividad;

    @Column(nullable = false)
    private Integer diasEntrenamiento;

    @Column(nullable = false)
    private Integer tiempoPorSesionMin;

    @Column(nullable = false)
    private String objetivo;

    @Column(nullable = false)
    private String restricciones;

    @Column(nullable = false)
    private String limitaciones;

    // Relación con usuario (no bidireccional, ligera, limpia)
    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;
}
