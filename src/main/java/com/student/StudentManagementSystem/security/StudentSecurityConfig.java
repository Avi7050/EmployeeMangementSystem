package com.student.StudentManagementSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager handleUserDetails(){
        UserDetails Avinash = User.builder()
                .username("Avinash")
                .password("{noop}Avinash@123")
                .roles("EMPLOYEE")
                .build();
        UserDetails Neeraj = User.builder()
                .username("Neeraj")
                .password("{noop}Neeraj@123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        return new InMemoryUserDetailsManager(Avinash,Neeraj);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests(Configurer ->
                    Configurer
                            .requestMatchers(HttpMethod.GET,"/api/employees","/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE,"/api/employees","/api/employees/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                    );
            http.httpBasic(Customizer.withDefaults());
            http.csrf(csrf -> csrf.disable());
            return http.build();
    }
}
