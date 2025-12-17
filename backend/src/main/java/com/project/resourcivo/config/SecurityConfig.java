package com.project.resourcivo.config;

import com.project.resourcivo.security.JwtAuthenticationFilter;
import com.project.resourcivo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        // Static resources
                        .requestMatchers("/", "/index.html", "/login.html", "/register.html", "/css/**", "/js/**",
                                "/images/**")
                        .permitAll()
                        // Swagger UI and API Docs
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api-docs/**")
                        .permitAll()

                        // Transport Booking endpoints (Specific exceptions for Students/Faculty)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/transport/booking/student/**")
                        .hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE,
                                "/api/transport/booking/student/**")
                        .hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/transport/booking/student/**")
                        .hasAnyRole("ADMIN", "STUDENT")

                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/transport/booking/faculty/**")
                        .hasAnyRole("ADMIN", "FACULTY")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE,
                                "/api/transport/booking/faculty/**")
                        .hasAnyRole("ADMIN", "FACULTY")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/transport/booking/faculty/**")
                        .hasAnyRole("ADMIN", "FACULTY")

                        // Transport Management endpoints
                        .requestMatchers("/api/transport/bookings").hasAnyRole("ADMIN", "TRANSPORT_MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/transport/**")
                        .hasAnyRole("ADMIN", "TRANSPORT_MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/transport/**")
                        .hasAnyRole("ADMIN", "TRANSPORT_MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.PATCH, "/api/transport/**")
                        .hasAnyRole("ADMIN", "TRANSPORT_MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/transport/**")
                        .hasAnyRole("ADMIN", "TRANSPORT_MANAGER")

                        // Transport Manager Profile endpoints
                        .requestMatchers("/api/transport-manager/**").hasAnyRole("ADMIN", "TRANSPORT_MANAGER")

                        // Admin only endpoints (keep generic admin last or specific exclusions first)
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Faculty endpoints
                        .requestMatchers("/api/faculty/**").hasAnyRole("ADMIN", "FACULTY")


                        // Student endpoints
                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "FACULTY", "STUDENT")

                        // Resource endpoints
                        .requestMatchers("/api/library-book/**", "/api/inventory-item/**", "/api/lab-equipment/**")
                        .hasAnyRole("ADMIN", "FACULTY", "STUDENT")

                        // Academic Endpoints (Course, Subject, Classroom)
                        // Read access for everyone
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/course/**", "/api/subject/**", "/api/classroom/**")
                        .hasAnyRole("ADMIN", "FACULTY", "STUDENT")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/course/search", "/api/subject/search", "/api/classroom/search")
                        .hasAnyRole("ADMIN", "FACULTY", "STUDENT")
                        // Write access for Admin only
                        .requestMatchers("/api/course/**", "/api/subject/**", "/api/classroom/**")
                        .hasRole("ADMIN")

                        // All other endpoints require authentication

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // For H2 Console
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
