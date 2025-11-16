package com.aitrain.nutrition_service.application.config;

import com.aitrain.nutrition_service.domain.model.gateway.PlanAlimentacionGateway;
import com.aitrain.nutrition_service.domain.usecase.PlanAlimentacionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanAlimentacionUseCaseConfig {
    @Bean
    public PlanAlimentacionUseCase planAlimentacionUseCase(PlanAlimentacionGateway planAlimentacionGateway) {
        return new PlanAlimentacionUseCase(planAlimentacionGateway);
    }
}
