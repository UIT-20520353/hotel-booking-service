package com.service.hotel_booking.config;

import com.service.hotel_booking.config.jwt.JwtProvider;
import com.service.hotel_booking.filter.AuthenticationFilter;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.UserSessionService;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Import(SecurityProblemSupport.class)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CorsFilter corsFilter;
    private final JwtProvider jwtProvider;
    private final SecurityProblemSupport problemSupport;
    private final UserSessionService userSessionService;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                         .requestMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(problemSupport)
                        .accessDeniedHandler(problemSupport)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(new AntPathRequestMatcher("swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("v3/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/common/**")).permitAll()

                        // For health check
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()

                        .requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/argent/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/location/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasAuthority("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/amenities", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/amenities/**")).hasAuthority("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/properties/**", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/properties", "POST")).hasAuthority("ARGENT")
                        .requestMatchers(new AntPathRequestMatcher("/api/rooms/**", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/rooms/**", "DELETE")).hasAuthority("ARGENT")
                        .requestMatchers(new AntPathRequestMatcher("/api/rooms", "POST")).hasAuthority("ARGENT")
                        .requestMatchers(new AntPathRequestMatcher("/api/banks")).hasAuthority("ARGENT")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new AuthenticationFilter(jwtProvider, userSessionService, userService),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }

}
