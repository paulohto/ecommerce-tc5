package com.ecommercetc5.loginusers.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                            .requestMatchers(HttpMethod.POST,"/auth/logout").permitAll()

                                .requestMatchers("/users/**").hasRole("ADMIN")
                                .requestMatchers("/complement/**").hasRole("ADMIN")
                                //.requestMatchers(HttpMethod.POST,"/products/**").hasRole("ADMIN")
                                .anyRequest().authenticated()

                        )
                
                //.httpBasic(Customizer.withDefaults()) //Formato para API
                //.formLogin(Customizer.withDefaults()) //Form Standard do Spring Security
                //.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Para trabalhar com JWT
                //.oauth2Login(Customizer.withDefaults()) //Formato para logar com autenticador OAUTH2 google
                //.oauth2ResourceServer(config -> { config.jwt(Customizer.withDefaults()); }) //Formato para caputar JWT do OAUTH2
                .addFilterBefore(securityFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
