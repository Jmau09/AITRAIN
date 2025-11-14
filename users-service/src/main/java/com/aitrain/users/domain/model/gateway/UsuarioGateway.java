package com.aitrain.users.domain.model.gateway;

import com.aitrain.users.domain.model.Usuario;

import java.util.List;

public interface UsuarioGateway {
    Usuario guardarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    Usuario buscarPorEmail(String email);
    void eliminarUsuario(String email);
    List<Usuario> listarUsuarios();

}

