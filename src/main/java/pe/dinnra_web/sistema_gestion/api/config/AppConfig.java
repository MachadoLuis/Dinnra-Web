package pe.dinnra_web.sistema_gestion.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/public/**",
                                "/usuario/**",
                                "/admin/**",
                                "/empleado/**",
                                "/css/**",
                                "/js/**",
                                "/script/**",
                                "/assets/**"
                        ).permitAll() // Permite acceso a estas rutas sin login
                        .anyRequest().authenticated()              // Otras requieren login
                )
                .formLogin(form -> form
                        .loginPage("/public/login")                // Página de login personalizada
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}
