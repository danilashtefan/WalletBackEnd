package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
