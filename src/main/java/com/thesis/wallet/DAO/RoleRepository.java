package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
