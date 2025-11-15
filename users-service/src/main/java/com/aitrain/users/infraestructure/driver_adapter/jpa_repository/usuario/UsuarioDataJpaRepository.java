package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface UsuarioDataJpaRepository extends JpaRepository <UsuarioData, String> {
    //cnsulta email a la base de datos
    Optional<UsuarioData>  findByEmail(String email);

}
