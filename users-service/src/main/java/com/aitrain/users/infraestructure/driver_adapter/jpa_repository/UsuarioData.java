package com.aitrain.users.infraestructure.driver_adapter.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "usuario")
@Data //solo se utiliza para el tema de la base de datos

public class UsuarioData {

    //las anotaciones para los atributos van encima del atributo, significa que afectan al atributo que estè debajo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cedula;

    private String nombre;
    private String apellido;
    @Column(unique = true, nullable = false)
    private String email;
    private String telefono;
    private String password;
    private Integer edad;

}
