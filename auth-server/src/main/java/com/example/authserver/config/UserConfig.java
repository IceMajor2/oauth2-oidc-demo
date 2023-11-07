package com.example.authserver.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserConfig {

    @Bean
    JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    ApplicationRunner userRunner(UserDetailsManager userDetailsManager) {
        return args -> {
            var builder = User.builder().roles("USER");
            var users = Map.of(
                    "icem", "{bcrypt}$2a$10$Grgbrewh9IOriVGqoOq/COu6u3eH.E84NXdZkQIL3rhVQ0CwYlGj6",
                    "admin", "{bcrypt}$2a$10$m.GLKllO4t2XlluDTA/HuOsravJWz73PqIzrDAUVwNDxBw4iBCFv6"
            );
            users.forEach((username, password) -> {
                if (!userDetailsManager.userExists(username)) {
                    var user = builder
                            .username(username)
                            .password(password)
                            .build();
                    userDetailsManager.createUser(user);
                }
            });
        };
    }
}
