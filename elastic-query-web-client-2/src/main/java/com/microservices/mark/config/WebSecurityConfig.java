package com.microservices.mark.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
public class WebSecurityConfig {

    private static final String GROUPS_CLAIM = "groups";

    private static final String ROLE_PREFIX = "ROLE_";

    @Value("${security.logout-success-url}")
    private String logoutSuccessUrl;

    private final ClientRegistrationRepository clientRegistrationRepository;

    public WebSecurityConfig(ClientRegistrationRepository registrationRepository) {
        this.clientRegistrationRepository = registrationRepository;
    }

    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(logoutSuccessUrl);
        return successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(conf -> conf.logoutSuccessHandler(oidcLogoutSuccessHandler()))
                .oauth2Client(Customizer.withDefaults())
                .oauth2Login(conf -> conf.userInfoEndpoint(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(
                    authority -> {
                        if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                            OidcIdToken oidcIdToken = oidcUserAuthority.getIdToken();
                            log.info("Username from id token: {}", oidcIdToken.getPreferredUsername());

                            OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();
                            List<SimpleGrantedAuthority> groupAuthorities = userInfo.getClaimAsStringList(GROUPS_CLAIM).stream()
                                    .map(group -> new SimpleGrantedAuthority(ROLE_PREFIX + group.toUpperCase()))
                                    .toList();

                            mappedAuthorities.addAll(groupAuthorities);
                        }
                    });
            return mappedAuthorities;
        };
    }
}
