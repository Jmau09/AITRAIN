package com.aitrain.users.infraestructure.security;

import com.aitrain.users.domain.model.gateway.EncrypterGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service

public class EncrypterGatewayImpl implements EncrypterGateway {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encrypt(String password) {

        return encoder.encode(password);
    }

    @Override
    public boolean checkPass(String passUser, String passBD) {
        return encoder.matches(passUser, passBD);
    }

}
