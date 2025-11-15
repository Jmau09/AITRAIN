package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario;


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
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(mapperUsuario::toUsuario)
                .orElse(null);
    }

    @Override
    public void eliminarUsuario(String email) {
        repository.deleteById(email);

    }
    @Override
    public List<Usuario> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(mapperUsuario::toUsuario)
                .toList();
    }

}
