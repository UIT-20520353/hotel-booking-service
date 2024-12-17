package spring.api.hotel_booking_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
import spring.api.hotel_booking_service.config.jwt.JwtProvider;
import spring.api.hotel_booking_service.helper.filter.AuthenticationFilter;
import spring.api.hotel_booking_service.service.UserService;
import spring.api.hotel_booking_service.service.UserSessionService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Import(SecurityProblemSupport.class)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityProblemSupport problemSupport;
    private final CorsFilter corsFilter;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserSessionService userSessionService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                         .requestMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(problemSupport)
                        .accessDeniedHandler(problemSupport))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(new AntPathRequestMatcher("swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("v3/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

                        .requestMatchers(new AntPathRequestMatcher("/api/common/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/authentication/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasAuthority("SYSTEM_ADMIN")

                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated())
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
