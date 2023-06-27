package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.outbound.adapters.entity.security.UserAuth;

public interface AuthPort {

    public UserAuth addUserAuth(UserAuth userAuth);
}
