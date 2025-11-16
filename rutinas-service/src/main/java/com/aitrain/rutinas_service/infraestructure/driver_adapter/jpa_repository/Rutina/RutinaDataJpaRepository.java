package com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutinaDataJpaRepository extends JpaRepository<RutinaData, Long> {
List<RutinaData> findByUserId(String userId);
}
