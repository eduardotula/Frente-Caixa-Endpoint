package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.security.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRespository extends JpaRepository<UserAuth, Integer> {
}
