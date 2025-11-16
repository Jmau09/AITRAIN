package com.aitrain.rutinas_service.infraestructure.entry_points;

import com.aitrain.rutinas_service.domain.model.Rutina;
import com.aitrain.rutinas_service.domain.model.gateway.RutinaGateway;
import com.aitrain.rutinas_service.domain.usecase.RutinaUseCase;
import com.aitrain.rutinas_service.infraestructure.driver_adapter.jpa_repository.Rutina.RutinaData;
import com.aitrain.rutinas_service.infraestructure.mapper.MapperRutina;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //indica que esta clase es un controlador, y se van a crear APIs que se van a exponer
@RequestMapping("/api/aitrain/rutinas") //parametrizar URLs
@RequiredArgsConstructor
public class RutinaController {

    private final RutinaUseCase rutinaUseCase;
    private final MapperRutina mapperRutina;

    @PostMapping("/save")
    public ResponseEntity<Rutina> createRutina(@RequestBody Rutina rutina) {
        Rutina creada = rutinaUseCase.createRutina(rutina);
        return ResponseEntity.ok(creada);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getById(@PathVariable Long id) {
        Rutina rutina = rutinaUseCase.getRutinaById(id);
        return ResponseEntity.ok().body(rutina);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Rutina>> getRutinasByUserId(@PathVariable String userId) {
        List<Rutina> rutinas = rutinaUseCase.getRutinasByUserId(userId);
        return ResponseEntity.ok(rutinas);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRutina(@PathVariable Long id) {
        rutinaUseCase.deleteRutina(id);
        return ResponseEntity.ok("Rutina eliminada correctamente");
    }
}
