package com.aitrain.users.application.config;

import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import com.aitrain.users.domain.usecase.ValoracionUseCase;
import org.springframework.context.annotation.Bean;

public class ValoracionUseCaseConfig {  @Bean
public ValoracionUseCase valoracionUseCase(ValoracionGateway valoracionGateway) {
    return valoracionUseCase(valoracionGateway);

}
}
