package com.aitrain.users.infraestructure.mapper;

import com.aitrain.users.domain.model.Admin;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Admin.AdminData;
import org.springframework.stereotype.Component;

@Component
public class MapperAdmin {
    public Admin toAdmin(AdminData adminData){
        if (adminData == null) return null;
        return new Admin(
                adminData.getEmail(),
                adminData.getPassword()
        );
    }

    public AdminData toAdminData(Admin admin){
        if (admin == null) return null;
        return new AdminData(
                admin.getEmail(),
                admin.getPassword()
        );
    }
}
