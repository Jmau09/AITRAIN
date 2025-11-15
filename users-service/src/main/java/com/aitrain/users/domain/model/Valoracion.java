package com.aitrain.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter


public class Valoracion {

    private Long id; // PK generada automáticamente
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

    // Este es el vínculo con Usuario
    private String emailUsuario;
}