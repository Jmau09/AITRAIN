package com.aitrain.users.infraestructure.mapper;

import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.infraestructure.driver_adapter.jpa_repository.Valoracion.ValoracionData;
import org.springframework.stereotype.Component;

@Component
public class MapperValoracion {
    public ValoracionData toEntity(Valoracion model) {
        if (model == null) return null;
        ValoracionData valoraciondata = new ValoracionData();

        valoraciondata.setId(model.getId());
        valoraciondata.setPesoKg(model.getPesoKg());
        valoraciondata.setEstaturaCm(model.getEstaturaCm());
        valoraciondata.setGenero(model.getGenero());
        valoraciondata.setImc(model.getImc());
        valoraciondata.setEdad(model.getEdad());
        valoraciondata.setNivelActividad(model.getNivelActividad());
        valoraciondata.setDiasEntrenamiento(model.getDiasEntrenamiento());
        valoraciondata.setTiempoPorSesionMin(model.getTiempoPorSesionMin());
        valoraciondata.setObjetivo(model.getObjetivo());
        valoraciondata.setRestricciones(model.getRestricciones());
        valoraciondata.setLimitaciones(model.getLimitaciones());
        valoraciondata.setEmailUsuario(model.getEmailUsuario());

        return valoraciondata;
    }

    public Valoracion toModel(ValoracionData data) {
        if (data == null) return null;
        Valoracion valoracionmodel = new Valoracion();

        valoracionmodel.setId(data.getId());
        valoracionmodel.setPesoKg(data.getPesoKg());
        valoracionmodel.setEstaturaCm(data.getEstaturaCm());
        valoracionmodel.setGenero(data.getGenero());
        valoracionmodel.setImc(data.getImc());
        valoracionmodel.setEdad(data.getEdad());
        valoracionmodel.setNivelActividad(data.getNivelActividad());
        valoracionmodel.setDiasEntrenamiento(data.getDiasEntrenamiento());
        valoracionmodel.setTiempoPorSesionMin(data.getTiempoPorSesionMin());
        valoracionmodel.setObjetivo(data.getObjetivo());
        valoracionmodel.setRestricciones(data.getRestricciones());
        valoracionmodel.setLimitaciones(data.getLimitaciones());
        valoracionmodel.setEmailUsuario(data.getEmailUsuario());

        return valoracionmodel;
    }
}