package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ValoracionDataJpaRepository extends JpaRepository<ValoracionData, Long> {
    Optional<ValoracionData> findByEmailUsuario(String emailUsuario);
    void deleteByEmailUsuario(String emailUsuario);



    ;
}
