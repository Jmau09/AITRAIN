package com.aitrain.users.application.config;

import com.aitrain.users.domain.model.gateway.UsuarioGateway;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import com.aitrain.users.domain.usecase.ValoracionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValoracionUseCaseConfig {
    @Bean
    public ValoracionUseCase valoracionUseCase(
            ValoracionGateway valoracionGateway,
            UsuarioGateway usuarioGateway) {
        return new ValoracionUseCase(valoracionGateway, usuarioGateway);
    }
}
