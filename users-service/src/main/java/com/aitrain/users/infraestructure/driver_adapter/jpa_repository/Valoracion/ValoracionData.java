package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Valoracion")
@Data

public class ValoracionData {
    private Long id; // recomendado
    private String emailUsuario; // FK hacia Usuario

    private Double pesoKg;
    private Double estaturaCm;
    private String genero;
    private Double imc;
    private Integer edad;

    private String nivelActividad;
    private Integer diasEntrenamiento;
    private Integer tiempoPorSesionMin;

    private String objetivo;
    private String restricciones;
    private String limitaciones;
}
