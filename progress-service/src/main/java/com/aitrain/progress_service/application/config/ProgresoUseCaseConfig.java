package com.aitrain.progress_service.application.config;


import com.aitrain.progress_service.domain.model.gateway.ProgresoGateway;
import com.aitrain.progress_service.domain.usecase.ProgresoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgresoUseCaseConfig {
    @Bean
    public ProgresoUseCase progresoUseCaseUseCase(ProgresoGateway progresoGateway) {
        return new ProgresoUseCase(progresoGateway);

    }
    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway, EncrypterGateway encrypterGateway) {
        return new UsuarioUseCase(usuarioGateway, encrypterGateway);
}
