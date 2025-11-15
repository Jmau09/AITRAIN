package com.aitrain.users.infraestructure.entry_points;

import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController //indica que esta clase es un controlador, y se van a crear APIs que se van a exponer
@RequestMapping("/api/aitrain/valoracion") //parametrizar URLs
@RequiredArgsConstructor
public class ValoracionController {
        private final ValoracionGateway valoracionGateway;


    @PostMapping
    public ResponseEntity<Valoracion> GuardarValoracion(@RequestBody Valoracion valoracion) {
        Valoracion creada = valoracionGateway.guardarValoracion(valoracion);
        return ResponseEntity.ok(creada);
    }

    // ⭐ Buscar valoración por ID
    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> BuscarValoracion(@PathVariable Long id) {
        Valoracion valoracion = valoracionGateway.buscarPorId(id);
        return valoracion != null ? ResponseEntity.ok(valoracion) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarValoracion(@PathVariable Long id) {
        valoracionGateway.eliminarValoracion(id);
        return ResponseEntity.ok().build();
    }
}

