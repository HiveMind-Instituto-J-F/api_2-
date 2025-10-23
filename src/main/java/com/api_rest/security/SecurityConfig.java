package com.api_rest.security;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    public final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(
                "https://area-restrita-5xoh.onrender.com",
                "http://localhost:5173"
        ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("teste"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("teste"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 1. ATIVA O CORS e utiliza a configuração do CorsSecurity
                .cors(Customizer.withDefaults())

                // 2. DESABILITA CSRF para APIs REST
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // 3. Permite OPTIONS para o Preflight do CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Garante que todas as rotas de API estejam cobertas com /**
                        .requestMatchers("/api/trabalhador/**").hasRole("ADMIN")
                        .requestMatchers("/api/firebase/**").hasRole("ADMIN")
                        .requestMatchers("/api/registro/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/manutencao/**").hasAnyRole("ADMIN", "USER") // Corrigido para cobrir sub-caminhos
                        .anyRequest().authenticated()
                )

                // 4. ATIVA A AUTENTICAÇÃO BÁSICA (leitura do header Authorization)
                .httpBasic(Customizer.withDefaults())

                .exceptionHandling(exception -> exception
                        // O Spring Security, por padrão, retorna 401 para falhas de httpBasic,
                        // mas vamos manter o AccessDeniedHandler para erros 403.
                        .accessDeniedHandler(customAccessDeniedHandler)
                );
        return httpSecurity.build();
    }
}