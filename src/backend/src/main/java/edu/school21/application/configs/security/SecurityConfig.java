package edu.school21.application.configs.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                   .authorizeHttpRequests(auth ->
                           auth.anyRequest()
                               .authenticated())
                   .formLogin(login ->
                           login.loginPage("/login")
                                .successHandler(successHandler())
                                .permitAll())
                   .logout(logout ->
                           logout.logoutUrl("/logout")
                                 .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                                 .invalidateHttpSession(true)
                                 .permitAll())
                   .exceptionHandling(exception ->
                           exception.authenticationEntryPoint(authenticationEntryPoint()))
                   .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        final SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        handler.setRedirectStrategy(new DisableRedirectStrategy());
        return handler;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}

