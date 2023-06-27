package com.loja65.frentecaixa.outbound.adapters.entity.security;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@UserDefinition
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_auth")
public class UserAuth extends PanacheEntity {

    @Username
    private String userName;

    @Password
    private String password;

    @Roles
    private String role;

    public UserAuth(String userName, String password, String role) {
        this.userName = userName;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role;
    }

}
