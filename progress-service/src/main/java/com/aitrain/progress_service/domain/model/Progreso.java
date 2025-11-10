package com.aitrain.progress_service.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter //OBTENER ATRIBUTO
public class Progreso {

    private Long id;
    private String cedulaUsuario;
    private Double peso;
    private Double altura;
    private Double imc;
    private Double grasaCorporal;
    private LocalDate fechaRegistro;
}
