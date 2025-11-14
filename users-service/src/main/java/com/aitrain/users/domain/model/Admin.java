package com.aitrain.users.domain.model;

import lombok.*;


@Data
@AllArgsConstructor //CREACION DEL CONSTRUCTOR
@NoArgsConstructor
@Setter //MODIFICA ATRIBUTO
@Getter
public class Admin {
    private String email;
    private String password;
}
