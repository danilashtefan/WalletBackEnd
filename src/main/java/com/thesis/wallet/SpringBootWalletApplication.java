package com.thesis.wallet;

import com.thesis.wallet.entity.security.Role;
import com.thesis.wallet.entity.security.User;
import com.thesis.wallet.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringBootWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWalletApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//
//			userService.saveUser(new User(null, "John Test", "john", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Jack Test", "jack", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("john", "ROLE_USER");
//
//			userService.addRoleToUser("jack", "ROLE_MANAGER" );
//
//			userService.addRoleToUser("jack", "ROLE_USER" );
		};
	}

}
