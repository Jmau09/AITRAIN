package com.aitrain.users.domain.model.gateway;

import com.aitrain.users.domain.model.Admin;

import java.util.List;

public interface AdminGateway {

    Admin guardarAdmin(Admin admin);

    Admin actualizarAdmin(Admin admin);

    Admin buscarPorEmail(String email);
    void eliminarAdmin(String email);

    boolean existePorEmail(String email);

    List<Admin> listarAdmins();
}
