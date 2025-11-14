package com.aitrain.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//SIEMPRE DEBEN IR ESTAS ETIQUETAS
@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter //OBTENER ATRIBUTO
public class Usuario {

//Atributos
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private String password;
    private Integer edad;

}
