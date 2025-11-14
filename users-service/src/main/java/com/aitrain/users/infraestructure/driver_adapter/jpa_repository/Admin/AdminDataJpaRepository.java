package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDataJpaRepository extends JpaRepository<AdminData, String> {
    Optional<AdminData> findByEmail(String email);
}
