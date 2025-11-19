package com.aitrain.users.domain.usecase;


import com.aitrain.users.domain.exceptions.EmailAlreadyExistException;
import com.aitrain.users.domain.exceptions.EmailEmptyException;
import com.aitrain.users.domain.exceptions.IncorrectCredentialsException;
import com.aitrain.users.domain.exceptions.UserNotFoundException;
import com.aitrain.users.domain.model.Notificacion;
import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.model.gateway.EncrypterGateway;
import com.aitrain.users.domain.model.gateway.NotificacionGateway;
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
    private final NotificacionGateway notificacionGateway;

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
        throw new EmailAlreadyExistException("Ya existe un usuario con esa email");
    }

    usuario.setPassword(encrypterGateway.encrypt(usuario.getPassword()));
    usuarioGateway.guardarUsuario(usuario);
    Usuario usuarioGuardado = usuarioGateway.guardarUsuario(usuario);
    Notificacion mensajeNotificacion = Notificacion.builder()
            .tipo("Registro Usuario")
            .email(usuarioGuardado.getEmail())
            .numeroTelefono(usuarioGuardado.getTelefono())
            .mensaje("Usuario registrado con exito")
            .build();

    notificacionGateway.enviarMensaje(mensajeNotificacion);

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
                throw new UserNotFoundException("No existe usuario con el correo: " + email);
            }
            usuarioGateway.eliminarUsuario(email);
            System.out.println("Usuario eliminado con éxito: " + email);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new EmailEmptyException("El email es obligatorio para actualizar");
        }

        Usuario usuarioExistente = usuarioGateway.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente == null) {
            // Retorna null si no existe
            throw new UserNotFoundException("Usuario no encontrado");
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

        if (usuarioLogueado == null) {
            throw new UserNotFoundException(
                    "No existe un usuario registrado con el email: " + email
            );
        }

        boolean passwordCorrecta = encrypterGateway.checkPass(password, usuarioLogueado.getPassword());

        if (!passwordCorrecta) {
            throw new IncorrectCredentialsException("La contraseña es incorrecta");
        }

        return "Credenciales correctas";
    }


    public List<Usuario> listarUsuarios() {
        return usuarioGateway.listarUsuarios();
    }


}

