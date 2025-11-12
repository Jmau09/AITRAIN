package com.aitrain.users.infraestructure.mapper;

import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class MapperUsuario {

    public Usuario toUsuario(UsuarioData usuarioData) {
        return new Usuario(
                usuarioData.getId(),
                usuarioData.getCedula(),
                usuarioData.getNombre(),
                usuarioData.getApellido(),
                usuarioData.getEmail(),
                usuarioData.getTelefono(),
                usuarioData.getPassword(),
                usuarioData.getEdad()
        );
    }

    public UsuarioData toData(Usuario usuario) {
        return new UsuarioData(
                usuario.getId(),
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getPassword(),
                usuario.getEdad()
        );
    }

}
