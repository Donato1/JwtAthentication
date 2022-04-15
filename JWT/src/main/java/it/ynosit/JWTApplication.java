package it.ynosit;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.ynosit.entity.Role;
import it.ynosit.entity.User;
import it.ynosit.service.UserService;

@SpringBootApplication
public class JWTApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWTApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/* Generare popolamento database
	@Bean
	CommandLineRunner run(UserService userService) {
		
		return args -> {
			
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "pippo", "pippo", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "paperino", "paperino", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "topolino", "topolino", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "pluto", "pluto", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("pippo", "ROLE_USER");
			userService.addRoleToUser("pippo", "ROLE_MANAGER");
			userService.addRoleToUser("paperino", "ROLE_MANAGER");
			userService.addRoleToUser("topolino", "ROLE_ADMIN");
			userService.addRoleToUser("pluto", "ROLE_ADMIN");
			userService.addRoleToUser("pluto", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("pluto", "ROLE_USER");
			
		};
		
	}*/
}
