package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Admin;

import com.aitrain.users.domain.model.Admin;
import com.aitrain.users.domain.model.gateway.AdminGateway;
import com.aitrain.users.infraestructure.mapper.MapperAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class AdminDataGatewayImpl  implements AdminGateway {
    private final AdminDataJpaRepository repository;
    private final MapperAdmin mapper;

    @Override
    public Admin guardarAdmin(Admin admin) {
        AdminData adminData = mapper.toAdminData(admin);
        return mapper.toAdmin(repository.save(adminData));
    }


    @Override
    public Admin buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toAdmin)
                .orElse(null);
    }

    @Override
    public void eliminarAdmin(String email) {
        repository.deleteById(email);
    }

    @Override
    public List<Admin> listarAdmins() {
        return List.of();
    }
}
