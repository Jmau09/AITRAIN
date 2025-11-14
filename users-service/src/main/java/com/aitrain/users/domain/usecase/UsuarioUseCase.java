package com.aitrain.users.domain.usecase;


import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.model.gateway.EncrypterGateway;
import com.aitrain.users.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;


//logica de negocio
//no agregar dependencias ni etiquetas
@RequiredArgsConstructor


public class UsuarioUseCase {
//Guardar,eliminar, buscar (van a ser el caso de uso y sera la logica de negocio)

    //no se puede contectar la BD en la infraestructura
    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;

public String guardarUsuario(Usuario usuario){
    if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
        return "El email es obligatorio";
    }
    if (usuario.getNombre()==null || usuario.getNombre().trim().isEmpty()){
        return "El campo nombre es obligatorio";
    }
    if (usuario.getApellido()==null || usuario.getApellido().trim().isEmpty()){
        return "El campo apellido es obligatorio";
    }
    if (usuario.getEmail()==null || usuario.getEmail().trim().isEmpty()){
        return "El campo email es obligatorio";
    }
    if (usuario.getPassword()==null || usuario.getPassword().trim().isEmpty()){
        return "El campo password es obligatorio";
    }
    if (usuario.getEdad()==null || usuario.getEdad() <= 0){
        return "El campo edad es obligatorio y debe ser mayor que 0";
    }

    Usuario existente = usuarioGateway.buscarPorEmail(usuario.getEmail());
    if (existente!=null){
        throw new IllegalArgumentException("Ya existe un usuario con esa email");
    }

    usuario.setPassword(encrypterGateway.encrypt(usuario.getPassword()));
    usuarioGateway.guardarUsuario(usuario);

    return "Usuario guardado correctamente";
}

    //public Usuario guardarUsuario(Usuario usuario) {

        //si da error, en los logs aparece ese mensaje
      //  if (usuario.getEmail() == null && usuario.getPassword() == null) {
            //Arrojar excepciones
        //    throw new NullPointerException("Ojo con eso manito - guardarUsuario");
        //}
        //usuario.setPassword(encrypterGateway.encrypt(usuario.getPassword()));
        //return usuarioGateway.guardar(usuario);
//        Forma 2
//        String cpasswordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
//        usuario.setPassword(cpasswordEncrypt);

    public Usuario buscarPorIdUsuario(String email) {
        try {
            Usuario usuario = usuarioGateway.buscarPorEmail(email);
            return usuario; // puede venir nulo si no existe
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }


    public void eliminarUsuario(String email) {
        try{
            Usuario usuario = usuarioGateway.buscarPorEmail(email);
            if(usuario==null){
                throw new IllegalArgumentException("No existe usuario con el correo: " + email);
            }
            usuarioGateway.eliminarUsuario(email);
            System.out.println("Usuario eliminado con éxito: " + email);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null) {
            throw new IllegalArgumentException("La cédula es obligatoria para actualizar");
        }

        Usuario usuarioExistente = usuarioGateway.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente == null) {
            // Retorna null si no existe
            return null;
        }

        // Mantener el ID original del usuario existente
        usuario.setEmail(usuarioExistente.getEmail());

        // Encriptar la contraseña solo si se envía una nueva
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);
        } else {
            // Mantener la contraseña anterior si no se envía una nueva
            usuario.setPassword(usuarioExistente.getPassword());
        }

        // Actualizar en base de datos
        return usuarioGateway.actualizarUsuario(usuario);
    }



    public String loginUsuario(String email, String password) {
        Usuario usuarioLogueado = usuarioGateway.buscarPorEmail(email);
        if (usuarioLogueado ==null) {
            return "Usuario con el email: " + email + " no existe";
        }

        if(encrypterGateway.checkPass(password, usuarioLogueado.getPassword())) {
            return "Credenciales correctos";
        } else  {
            return "Credenciales incorrectos";
        }
    }
    public List<Usuario> listarUsuarios() {
        return usuarioGateway.listarUsuarios();
    }


}

