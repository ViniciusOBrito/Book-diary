package com.brito.bookdiary.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private SecurityFilter securityFilter;

    private static final String[] ENDPOINTS_RELEASED = {
            "api/auth/user/login",
            "api/auth/user/register",
            "api/auth/admin/login",
            "api/auth/admin/register"
    };

    private static final String[] ENDPOINTS_USER_ADMIN_AUTHORIZATION = {
            "api/"
    };

    private static final String[] ENDPOINTS_ADMIN_AUTHORIZATION = {
            "api/"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(ENDPOINTS_RELEASED).permitAll()
                        .requestMatchers(ENDPOINTS_USER_ADMIN_AUTHORIZATION).hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(ENDPOINTS_ADMIN_AUTHORIZATION).hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
