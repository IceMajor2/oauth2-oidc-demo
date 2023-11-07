package com.example.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService() {
		var userBuilder = User.builder();
		return new InMemoryUserDetailsManager(
				userBuilder.roles("USER").username("icem").password("{bcrypt}$2a$10$Grgbrewh9IOriVGqoOq/COu6u3eH.E84NXdZkQIL3rhVQ0CwYlGj6").build(),
				userBuilder.roles("ADMIN").username("admin").password("{bcrypt}$2a$10$m.GLKllO4t2XlluDTA/HuOsravJWz73PqIzrDAUVwNDxBw4iBCFv6").build()
		);
	}
}
