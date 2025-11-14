package com.aitrain.users.domain.usecase;

import com.aitrain.users.domain.model.Admin;
import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.model.gateway.AdminGateway;
import com.aitrain.users.domain.model.gateway.EncrypterGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AdminUseCase {

    private final AdminGateway adminGateway;
    private final EncrypterGateway encrypterGateway;

    public String guadarAdmin(Admin admin) {
        if (admin.getEmail() == null || admin.getEmail().trim().isEmpty()) {
            return "El email es obligatorio";
        }
            if (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {
                return "El campo password es obligatorio";
            }

            Admin existente = adminGateway.buscarPorEmail(admin.getEmail());
            if (existente != null) {
                throw new IllegalArgumentException("Ya existe un usuario con esa email");
            }

            admin.setPassword(encrypterGateway.encrypt(admin.getPassword()));
            adminGateway.guardarAdmin(admin);

            return "Usuario guardado correctamente";
        }

    public Admin buscarAdmin (String email) {
        try {
            Admin admin = adminGateway.buscarPorEmail(email);
            return admin; // puede venir nulo si no existe
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }

    public void eliminarAdmin(String email) {
        try{
             Admin admin= adminGateway.buscarPorEmail(email);
            if(admin==null){
                throw new IllegalArgumentException("No existe usuario con el correo: " + email);
            }
            adminGateway.eliminarAdmin(email);
            System.out.println("Admin eliminado con éxito: " + email);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Admin> listarAdmins() {
        return adminGateway.listarAdmins();
    }

    public String loginAdmin(String email, String password) {
        Admin adminLogueado = adminGateway.buscarPorEmail(email);
        if (adminLogueado ==null) {
            return "Admin con el email: " + email + " no existe";
        }

        if(encrypterGateway.checkPass(password, adminLogueado.getPassword())) {
            return "Credenciales correctos";
        } else  {
            return "Credenciales incorrectos";
        }
    }

}
