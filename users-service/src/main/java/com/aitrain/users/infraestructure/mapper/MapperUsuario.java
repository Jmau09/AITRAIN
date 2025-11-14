package com.aitrain.users.infraestructure.mapper;

import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class MapperUsuario {

    public Usuario toUsuario(UsuarioData usuarioData) {
        return new Usuario(
                usuarioData.getEmail(),
                usuarioData.getNombre(),
                usuarioData.getApellido(),
                usuarioData.getTelefono(),
                usuarioData.getPassword(),
                usuarioData.getEdad()

        );
    }

    public UsuarioData toData(Usuario usuario) {
        return new UsuarioData(
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getPassword(),
                usuario.getEdad()
        );
    }

}
