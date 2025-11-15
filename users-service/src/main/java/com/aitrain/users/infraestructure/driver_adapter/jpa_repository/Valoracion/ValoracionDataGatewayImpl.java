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
    private final ValoracionDataJpaRepository ValoracionRepository ;


    @Override
    public Valoracion guardarValoracion(Valoracion valoracion) {
        // convertir dominio -> entidad, guardar y convertir entidad -> dominio
        return mapperValoracion.toModel(
                ValoracionRepository.save(mapperValoracion.toEntity(valoracion))
        );
    }

    @Override
    public Valoracion buscarPorId(Long id) {
        return ValoracionRepository.findById(id)
                .map(mapperValoracion::toModel)
                .orElse(null);
    }

    @Override
    public void eliminarValoracion(Long id) {
        ValoracionRepository.deleteById(id);
    }
}