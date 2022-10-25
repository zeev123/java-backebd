package com.hotelsecure;

import com.hotelsecure.service.UserService;
import com.hotelsecure.tables.Role;
import com.hotelsecure.tables.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HotelSecureApplication  {
	public static void main(String[] args) {
		SpringApplication.run(HotelSecureApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.seveRole(new Role(null, "ROLE_RECEPTION"));
//			userService.seveRole(new Role(null, "ROLE_MANAGER"));
//			userService.seveRole(new Role(null, "ROLE_OWNER"));
//
//			userService.seveUser(new User(null,"Zeev Resner", "zeev","1234", new ArrayList<>()));
//			userService.seveUser(new User(null,"MOSHE", "MOSHE","1234", new ArrayList<>()));
//			userService.seveUser(new User(null,"doodi", "doodi","1234", new ArrayList<>()));
//			userService.seveUser(new User(null,"jo biden", "jo","1234", new ArrayList<>()));
//			userService.addRoleToUser("zeev","ROLE_RECEPTION");
//			userService.addRoleToUser("MOSHE","ROLE_RECEPTION");
//			userService.addRoleToUser("doodi","ROLE_RECEPTION");
//			userService.addRoleToUser("doodi","ROLE_MANAGER");
//			userService.addRoleToUser("jo","ROLE_RECEPTION");
//			userService.addRoleToUser("jo","ROLE_MANAGER");
//			userService.addRoleToUser("jo","ROLE_OWNER");
//		};
//	}

}

