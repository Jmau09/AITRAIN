package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValoracionDataJpaRepository extends JpaRepository<ValoracionData, Integer> {

    Optional<ValoracionData> findById(Integer integer);
}
