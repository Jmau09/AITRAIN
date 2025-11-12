package com.aitrain.users.infraestructure.driver_adapter.jpa_repository;


import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.model.gateway.UsuarioGateway;
import com.aitrain.users.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //indica que la clase almacena, guarda, elimina en la base de datos, tiene sql
//esa clase hace las consultas a la BD

@RequiredArgsConstructor
public class UsuarioDataGatewayImpl implements UsuarioGateway {


    //los final requieren un constructor
    private final MapperUsuario mapperUsuario;
    private final UsuarioDataJpaRepository repository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarUsuarioPorCedula(String cedula) {
        try{
            repository.deleteByCedula(cedula);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Usuario buscarUsuarioPorID(Long id) {
        return repository.findById(id)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElse(null);
    }



    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        //se valida si el usuario existe o no existe
        //primero mapear el usuario
        // JPA recibe enmtidades
        UsuarioData usuarioData = mapperUsuario.toData(usuario);

        if (!repository.existsById(usuario.getId())) {
           throw new RuntimeException("Usuario con Id " + usuario.getId() + "no existe");
        }
        //sirve para guardar y actualizar. si no hay ID, crea. Si existe, actualiza
        return mapperUsuario.toUsuario(repository.save(usuarioData));

    }

    @Override
    public Usuario findByCedula(String cedula) {
        return repository.findByCedula(cedula)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {
        try {
            return repository.findByEmail(email)
                    .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                    .orElse(null);
        } catch (Exception e) {
            System.out.println("Error al consultar  email en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return List.of();
    }


}
