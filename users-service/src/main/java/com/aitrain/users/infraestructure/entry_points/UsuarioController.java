package com.aitrain.users.infraestructure.entry_points;

import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.usecase.UsuarioUseCase;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario.UsuarioData;
import com.aitrain.users.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //indica que esta clase es un controlador, y se van a crear APIs que se van a exponer
@RequestMapping("/api/aitrain/usuario") //parametrizar URLs
@RequiredArgsConstructor //crea constructores
public class UsuarioController {

    //disparador
    //las APIs las consume el front
    //El front va conectado a un botòn
    //el boton consume la api

    private final UsuarioUseCase usuarioUseCase;
    private final MapperUsuario mapperUsuario;

    @PostMapping("/save")
    public ResponseEntity<String> saveUsuario(@RequestBody UsuarioData usuarioData) {
        Usuario usuario = mapperUsuario.toUsuario(usuarioData);
        String resultado = usuarioUseCase.guardarUsuario(usuario);

        if (resultado.startsWith("Usuario guardado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        if (resultado.contains("Ya existe un usuario" )) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("buscar/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        try {
            Usuario usuarioEncontrado = usuarioUseCase.buscarPorIdUsuario(email);

            if (usuarioEncontrado == null) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.ok(usuarioEncontrado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @DeleteMapping("/eliminar/{email}")
    //que pase el obj por la URL, y no por un body
    public ResponseEntity<String>eliminarUsuario(@PathVariable String email) {
        try {
            Usuario usuario = usuarioUseCase.buscarPorIdUsuario(email);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("El usuario con el email:" + email + " no exite en la BD");
            }
            usuarioUseCase.eliminarUsuario(email);
            //siempre se debe retornar un HTTP status
            return ResponseEntity.ok().body("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUsuario(@RequestBody UsuarioData usuarioData) {

        Usuario usuario = mapperUsuario.toUsuario(usuarioData);
        Usuario usuarioActualizado = usuarioUseCase.actualizarUsuario(usuario);

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }



    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioUseCase.listarUsuarios());
    }




}

