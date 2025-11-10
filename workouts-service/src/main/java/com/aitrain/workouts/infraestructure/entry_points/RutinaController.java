package com.aitrain.workouts.infraestructure.entry_points;

import com.aitrain.workouts.domain.model.Rutina;
import com.aitrain.workouts.domain.usecase.RutinaUseCase;
import com.aitrain.workouts.infraestructure.driver_adapters.jpa_repository.RutinaData;
import com.aitrain.workouts.infraestructure.mapper.MapperRutina;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aitrain/rutinas")
@RequiredArgsConstructor

public class RutinaController {

    private final RutinaUseCase rutinaUseCase;
    private final MapperRutina mapperRutina;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarRutina(@RequestBody RutinaData rutinaData){
        Rutina rutina = mapperRutina.toRutina(rutinaData);
        String resultado = rutinaUseCase.guardarRutina(rutina);

        if (resultado.startsWith("Rutina Guardada")) {
            return  new ResponseEntity<>(resultado, HttpStatus.OK);
     }  else  {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
         }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> buscarRutinaId(@PathVariable Long id) {
        try {
            Rutina rutinaEncontrada = rutinaUseCase.buscarRutinaPorId(id);

            if (rutinaEncontrada == null) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.ok(rutinaEncontrada);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @DeleteMapping("/{id}")
    //que pase el obj por la URL, y no por un body
    public ResponseEntity<String> eliminarRutinaId(@PathVariable Long id){
        try {
            Rutina rutina = rutinaUseCase.buscarRutinaPorId(id);
            if(rutina==null){
                return ResponseEntity.status(HttpStatus.OK)
                        .body("La Rutina con el ID:"+id+" no exite en la BD");
            }
            rutinaUseCase.eliminarRutinaPorId(id);
            //siempre se debe retornar un HTTP status
            return ResponseEntity.ok().body("Rutina eliminada exitosamente");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<String> actualizarRutina(@RequestBody RutinaData  rutinaData) {
        try {
            Rutina rutina = mapperRutina.toRutina(rutinaData);
            Rutina rutinActualizada = rutinaUseCase.actualizarRutina(rutina);

            if (rutinActualizada == null) {
                return new ResponseEntity<>("La rutina no existe en la base de datos", HttpStatus.OK);
            }

            return new ResponseEntity<>("Rutina actualizada correctamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> listarRutinas() {
        try {
            List<Rutina> rutinas = rutinaUseCase.obtenerRutinas();
            if (rutinas.isEmpty()) {
                return ResponseEntity.ok("No hay rutinas registradas actualmente");
            }
            return ResponseEntity.ok(rutinas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al listar las rutinas");
        }
    }


}
