package com.aitrain.progress_service.infraestructure.entry_points;


import com.aitrain.progress_service.domain.model.Progreso;
import com.aitrain.progress_service.domain.usecase.ProgresoUseCase;
import com.aitrain.progress_service.infraestructure.driver_adapters.jpa_repository.ProgresoData;
import com.aitrain.progress_service.infraestructure.mapper.MapperProgreso;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aitrain/progreso")
@RequiredArgsConstructor

public class ProgresoController {

    private final ProgresoUseCase progresoUseCase;
    private final MapperProgreso mapperProgreso;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarProgreso(@RequestBody ProgresoData progresoData){
        Progreso progreso = mapperProgreso.toProgreso(progresoData);
        String resultado = progresoUseCase.guardarProgreso(progreso);

        if (resultado.startsWith("Progreso Guardado")) {
            return  new ResponseEntity<>(resultado, HttpStatus.OK);
        }  else  {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progreso> buscarProgresoId(@PathVariable Long id) {
        try {
            Progreso progresoEncontrado = progresoUseCase.obtenerPorId(id);

            if (progresoEncontrado == null) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.ok(progresoEncontrado);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/verProgreso")
    public ResponseEntity<List<Progreso>> obtenerTodos() {
        return ResponseEntity.ok(progresoUseCase.obtenerTodos());
    }

    @DeleteMapping("/{id}")
    //que pase el obj por la URL, y no por un body
    public ResponseEntity<String> eliminarProgresoId(@PathVariable Long id){
        try {
            Progreso progreso = progresoUseCase.obtenerPorId(id);
            if(progreso==null){
                return ResponseEntity.status(HttpStatus.OK)
                        .body("No hay ningun proceso asociado al ID:"+id+" no exite en la BD");
            }
            progresoUseCase.eliminarPorId(id);
            //siempre se debe retornar un HTTP status
            return ResponseEntity.ok().body("Rutina eliminada exitosamente");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
