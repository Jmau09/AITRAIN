package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario;

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
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer edad;

}
