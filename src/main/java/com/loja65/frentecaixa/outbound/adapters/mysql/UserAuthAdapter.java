package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.outbound.adapters.entity.security.UserAuth;
import com.loja65.frentecaixa.outbound.adapters.repositories.UserAuthRespository;
import com.loja65.frentecaixa.outbound.port.AuthPort;

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
