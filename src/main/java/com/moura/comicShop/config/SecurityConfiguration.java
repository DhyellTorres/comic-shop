package com.moura.comicShop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.moura.comicShop.user.Permission.ADMIN_CREATE;
import static com.moura.comicShop.user.Permission.ADMIN_DELETE;
import static com.moura.comicShop.user.Permission.ADMIN_READ;
import static com.moura.comicShop.user.Permission.ADMIN_UPDATE;
import static com.moura.comicShop.user.Permission.MANAGER_CREATE;
import static com.moura.comicShop.user.Permission.MANAGER_DELETE;
import static com.moura.comicShop.user.Permission.MANAGER_READ;
import static com.moura.comicShop.user.Permission.MANAGER_UPDATE;
import static com.moura.comicShop.user.Role.ADMIN;
import static com.moura.comicShop.user.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/v1/comics/**")
        .permitAll()

        .requestMatchers("/api/v1/comics/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers(GET, "/api/v1/comics/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
        .requestMatchers(POST, "/api/v1/comics/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/api/v1/comics/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/comics/**").hasAuthority(ADMIN_DELETE.name())

        .requestMatchers("/api/v1/coupon/**").hasRole(ADMIN.name())
        .requestMatchers(GET, "/api/v1/coupon/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(POST, "/api/v1/coupon/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/api/v1/coupon/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/coupon/**").hasAuthority(ADMIN_DELETE.name())

        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

    return http.build();
  }
}
