package com.paulonepotti.microservices.auth_microservice.application.port.in;

import com.paulonepotti.microservices.auth_microservice.domain.model.User;

public interface AuthUseCase {

    User register(String email, String password);
    String login(String email, String password);

}
