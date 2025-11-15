package com.aitrain.users.infraestructure.entry_points;

import com.aitrain.users.domain.exceptions.AdminNotFoundException;
import com.aitrain.users.domain.exceptions.IncorrectCredentialsException;
import com.aitrain.users.domain.exceptions.UserNotFoundException;
import com.aitrain.users.domain.usecase.AdminUseCase;
import com.aitrain.users.domain.usecase.UsuarioUseCase;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Admin.AdminData;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.usuario.UsuarioData;
import com.aitrain.users.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //indica que esta clase es un controlador, y se van a crear APIs que se van a exponer
@RequestMapping("/api/aitrain/auth") //parametrizar URLs
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioUseCase usuarioUseCase;
    private final AdminUseCase adminUseCase;

    @PostMapping("/loginUsuario")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioData usuarioData) {
        try {
            String respuesta = usuarioUseCase.loginUsuario(
                    usuarioData.getEmail(),
                    usuarioData.getPassword()
            );
            return ResponseEntity.ok(respuesta); // Credenciales correctas
        } catch (UserNotFoundException | IncorrectCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar la solicitud");
        }
    }



    @PostMapping("/loginAdmin")
    public ResponseEntity<String> loginAdmin(@RequestBody AdminData adminData) {
        try {
            String respuesta = adminUseCase.loginAdmin(adminData.getEmail(), adminData.getPassword());
            return ResponseEntity.ok(respuesta); // Credenciales correctas
        } catch (AdminNotFoundException | IncorrectCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar la solicitud");
        }
    }

}


