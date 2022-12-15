package com.loja65.outbound.adapters.mysql;

import com.loja65.outbound.adapters.entity.security.UserAuth;
import com.loja65.outbound.adapters.repositories.UserAuthRespository;
import com.loja65.outbound.port.AuthPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserAuthAdapter implements AuthPort {

    @Inject
    UserAuthRespository respository;

    @Override
    public UserAuth addUserAuth(UserAuth userAuth) {
        return respository.save(userAuth);
    }
}
