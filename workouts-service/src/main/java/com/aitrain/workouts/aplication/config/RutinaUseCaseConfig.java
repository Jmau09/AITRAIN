package com.aitrain.workouts.aplication.config;


import com.aitrain.workouts.domain.model.gateway.RutinaGateway;
import com.aitrain.workouts.domain.usecase.RutinaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RutinaUseCaseConfig {
    @Bean
    public RutinaUseCase rutinaUseCase(RutinaGateway rutinaGateway) {
        return new RutinaUseCase(rutinaGateway);
    }
}
