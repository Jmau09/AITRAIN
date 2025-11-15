package com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion;

import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import com.aitrain.users.infraestructure.mapper.MapperValoracion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository //indica que la clase almacena, guarda, elimina en la base de datos, tiene sql
//esa clase hace las consultas a la BD

@RequiredArgsConstructor
public class ValoracionDataGatewayImpl implements ValoracionGateway {
    private final MapperValoracion  mapperValoracion;
    private final ValoracionDataGatewayImpl repository ;

    @Override
    public Valoracion guardarValoracion(Valoracion valoracion) {
        return mapperValoracion.toModel(
                repository.wait(mapperValoracion.toEntity(valoracion))
        );
    }

    @Override
    public Valoracion buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapperValoracion::toModel)
                .orElse(null);
    }

    @Override
    public void eliminarValoracion(Long id) {
        repository.deleteById(id);
    }
}