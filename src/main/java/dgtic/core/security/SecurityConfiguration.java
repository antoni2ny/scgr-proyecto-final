package dgtic.core.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import dgtic.core.security.jwt.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import dgtic.core.security.jwt.JwtAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/", "/login", "/refresh-token", "/bootstrap/**", "/iconos/**",
                                "/image/**", "/css/**", "/js/**", "/images/**", "/webjars/**"
                        ).permitAll()

                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        .requestMatchers("/bitacora/**").hasRole("ADMIN")

                        .requestMatchers("/reportes/nuevo/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/reportes/*/editar/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/reportes/*/eliminar/**").hasAnyRole("ADMIN", "USER")

                        .requestMatchers("/principal/**", "/reportes/**")
                        .hasAnyRole("ADMIN", "USER", "CONSULTA")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(jwtAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .deleteCookies(
                                "accessToken",
                                "refreshToken",
                                "JSESSIONID"
                        )
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/sin-acceso")
                )
                .addFilterBefore(jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}