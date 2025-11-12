package com.aitrain.users.domain.model.gateway;
//los metodos (guardar, registrar usuario, etc)

//puerta de enlace de dominio a infraestructura

import com.aitrain.users.domain.model.Usuario;

import java.util.List;

//Funcion:nombre del funcion, lo que se va retornar, patrones de entrada y logica de la funcion
public interface UsuarioGateway {

    //Guardar,eliminar, buscar van a ser el caso de uso y sera la logica de negocio
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuarioPorCedula (String cedula);
    Usuario buscarUsuarioPorID(Long id);
    Usuario actualizarUsuario(Usuario usuario);
    //busca al usuario por email
    Usuario findByCedula(String cedula);
    Usuario findByEmail(String email);
    List<Usuario> listarUsuarios();

}

