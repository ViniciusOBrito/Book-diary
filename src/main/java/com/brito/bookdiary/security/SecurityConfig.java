package com.brito.bookdiary.security;


import com.brito.bookdiary.user.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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



    private final String[] ENDPOINTS_RELEASED = {
            "/api/auth/login",
            "api/auth/register",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final String[] ENDPOINTS_ADMIN = {
            "/api/books",
            "api/authors",
            "/api/publishers",
            "/api/bookshelfs"
    };

    private final String[] ENDPOINTS_ADMIN_USER = {
            "/api/books",
            "/api/posts/**",
            "/api/bookshelfs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( ENDPOINTS_RELEASED).permitAll()

                        .requestMatchers(HttpMethod.POST,  ENDPOINTS_ADMIN).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_ADMIN).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ENDPOINTS_ADMIN).hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, ENDPOINTS_ADMIN_USER).hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST,"/api/posts").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyAuthority("ADMIN", "USER")

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
