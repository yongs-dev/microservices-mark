package com.microservices.mark.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserConfigData userConfigData;

    @Value("${security.paths-to-ignore}")
    private String[] pathsToIgnore;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(Arrays.stream(pathsToIgnore).map(AntPathRequestMatcher::new).toList().toArray(new RequestMatcher[]{})).permitAll()
                        .requestMatchers("/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername(userConfigData.getUsername())
                .password(passwordEncoder().encode(userConfigData.getPassword()))
                .roles(userConfigData.getRoles())
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
