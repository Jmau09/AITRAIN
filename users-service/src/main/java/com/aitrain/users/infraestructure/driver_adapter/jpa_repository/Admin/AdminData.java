package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminData {

    @Id
    private String email;
    @Column(nullable = false)
    private String password;
}