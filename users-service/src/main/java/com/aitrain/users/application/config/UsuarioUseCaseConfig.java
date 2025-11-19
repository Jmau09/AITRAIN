package com.aitrain.users.application.config;


import com.aitrain.users.domain.model.gateway.*;
import com.aitrain.users.domain.usecase.AdminUseCase;
import com.aitrain.users.domain.usecase.UsuarioUseCase;
import com.aitrain.users.domain.usecase.ValoracionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCaseConfig {

    //por cada caso de uso se agrega eso
    @Bean //indicar a spring que esa clase es un componente
    //por cada caso de uso se agrega eso
    public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway,
                                         EncrypterGateway encrypterGateway,
                                         NotificacionGateway notificacionGateway) {
        return new UsuarioUseCase(usuarioGateway, encrypterGateway, notificacionGateway);
    }

    @Bean
   public AdminUseCase adminUseCase(AdminGateway adminGateway,
                                    EncrypterGateway encrypterGateway)  {
        return new AdminUseCase(adminGateway, encrypterGateway);
    }




}