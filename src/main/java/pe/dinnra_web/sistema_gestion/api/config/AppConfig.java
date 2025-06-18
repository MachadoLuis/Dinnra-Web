package pe.dinnra_web.sistema_gestion.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.dinnra_web.sistema_gestion.api.util.JwtFilter;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String [] postEndpointsAdmins = {
                "/api/v1/user/user-employee",
                "/api/v1/position",
                "/api/v1/room",
                "/api/v1/employee"
        };

        String [] getEndpointsAdmins = {
                "/api/v1/user",
                "/api/v1/user/**",
                "/api/v1/position",
                "/api/v1/position/**",
                "/api/v1/employee",
                "/api/v1/employee/**",
                "/api/v1/client",
                "/api/v1/client/**",
                "/admin/**",
        };

        String [] getEndpointsRole = {
                "/usuario/rooms",
                "/api/v1/room",
                "/api/v1/room/**"
        };

        String [] postEndpointsClient = {
                "/usuario/rooms",
        };


        return  http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/ws/**",
                                "/favicon.ico",
                                "/api/v1/auth/**",
                                "/api/v1/auth/validate-auth",
                                "/api/v1/auth/logout",
                                "/api/v1/user/user-client",
                                "/api/v1/contact-service/**",
                                "/api/v1/client",
                                "/public/**",
                                "/empleado/**",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/script/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, postEndpointsAdmins).hasAuthority("Admin")
                        .requestMatchers(HttpMethod.GET, getEndpointsAdmins).hasAuthority("Admin")
                        .requestMatchers(HttpMethod.GET, getEndpointsRole).hasAnyAuthority("Admin", "Client")
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
