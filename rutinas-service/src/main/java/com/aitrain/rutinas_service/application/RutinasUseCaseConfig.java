package com.aitrain.rutinas_service.application;

import com.aitrain.rutinas_service.domain.model.gateway.RutinaGateway;
import com.aitrain.rutinas_service.domain.usecase.RutinaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RutinasUseCaseConfig {
    @Bean
    public RutinaUseCase rutinaUseCase(RutinaGateway rutinaGateway) {
        return new RutinaUseCase(rutinaGateway);
    }

}

