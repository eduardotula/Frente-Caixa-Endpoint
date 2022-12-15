package com.loja65.outbound.port;

import com.loja65.outbound.adapters.entity.security.UserAuth;

public interface AuthPort {

    public UserAuth addUserAuth(UserAuth userAuth);
}
