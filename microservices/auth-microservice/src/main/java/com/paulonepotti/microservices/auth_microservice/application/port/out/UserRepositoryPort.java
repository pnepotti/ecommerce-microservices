package com.paulonepotti.microservices.auth_microservice.application.port.out;

import com.paulonepotti.microservices.auth_microservice.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);
    User findByEmail(String email);
    User findById(String id);
    void deleteById(String id);
    void update(User user);

}
