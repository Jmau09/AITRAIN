package com.aitrain.progress_service.infraestructure.mapper;


import com.aitrain.progress_service.domain.model.Progreso;
import com.aitrain.progress_service.infraestructure.driver_adapters.jpa_repository.ProgresoData;
import org.springframework.stereotype.Component;

@Component
public class MapperProgreso {
   public Progreso toProgreso(ProgresoData progresoData){
       return new Progreso(
               progresoData.getId(),
               progresoData.getCedulaUsuario(),
               progresoData.getPeso(),
               progresoData.getAltura(),
               progresoData.getImc(),
               progresoData.getGrasaCorporal(),
               progresoData.getFechaRegistro()
       );

   }

   public ProgresoData toProgresoData(Progreso progreso){
       return new ProgresoData(
               progreso.getId(),
               progreso.getCedulaUsuario(),
               progreso.getPeso(),
               progreso.getAltura(),
               progreso.getImc(),
               progreso.getGrasaCorporal(),
               progreso.getFechaRegistro()
       );
   }
}
