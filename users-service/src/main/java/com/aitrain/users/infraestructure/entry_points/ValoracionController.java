package com.aitrain.users.infraestructure.entry_points;

import com.aitrain.users.domain.exceptions.ValoracionInvalidDataException;
import com.aitrain.users.domain.exceptions.ValoracionNotFoundException;
import com.aitrain.users.domain.exceptions.ValoracionYaExisteException;
import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import com.aitrain.users.domain.usecase.ValoracionUseCase;
import com.aitrain.users.infraestructure.DTO.RespuestaValoracion;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion.ValoracionData;
import com.aitrain.users.infraestructure.mapper.MapperValoracion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController //indica que esta clase es un controlador, y se van a crear APIs que se van a exponer
@RequestMapping("/api/aitrain/valoracion") //parametrizar URLs
@RequiredArgsConstructor
public class ValoracionController {

    private final ValoracionUseCase valoracionUseCase;
    private final MapperValoracion mapperValoracion;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarValoracion(@RequestBody Valoracion valoracion) {
        try {
            valoracionUseCase.crearValoracion(valoracion.getEmailUsuario(), valoracion);
            return ResponseEntity.ok("Valoración guardada correctamente");

        } catch (ValoracionInvalidDataException |
                 ValoracionYaExisteException |
                 ValoracionNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }

        @GetMapping("/buscar/{email}")
        public ResponseEntity<RespuestaValoracion> obtenerValoracion(@PathVariable String email) {
            try {
                Valoracion valoracion = valoracionUseCase.obtenerValoracionPorEmail(email);

                ValoracionData data = mapperValoracion.toEntity(valoracion);

                return ResponseEntity.ok(new RespuestaValoracion("Valoración encontrada", data));

            } catch (ValoracionInvalidDataException | ValoracionNotFoundException e) {

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new RespuestaValoracion(e.getMessage(), null));

            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new RespuestaValoracion("Error interno del servidor", null));
            }
        }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarValoracion(@RequestBody ValoracionData valoracionData) {
        try {
            Valoracion valoracion = mapperValoracion.toModel(valoracionData);

            valoracionUseCase.actualizarValoracion(valoracionData.getEmailUsuario(), valoracion);

            return ResponseEntity.ok("Valoración actualizada correctamente");

        } catch (ValoracionInvalidDataException |
                 ValoracionNotFoundException e) {

            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }

    @DeleteMapping("/eliminar/{email}")
    public ResponseEntity<String> eliminarValoracion(@PathVariable String email) {

        try {
            valoracionUseCase.eliminarValoracion(email);
            return ResponseEntity.ok("Valoración eliminada correctamente");

        } catch (ValoracionNotFoundException |
                 ValoracionInvalidDataException e) {

            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }




}