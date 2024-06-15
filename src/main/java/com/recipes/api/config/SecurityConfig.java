package com.recipes.api.config;

import com.recipes.api.security.FirebaseTokenFilter;
import com.recipes.api.security.JWTAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {
    private final FirebaseTokenFilter firebaseTokenFilter;

    private final JWTAuthenticationEntryPoint unauthorizedHandler;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final String IMAGES = "/images/**";

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    Set<String> whiteList = Set.of(
                            IMAGES
                    );

                    auth.requestMatchers(whiteList.toArray(new String[0])).permitAll()
                            .anyRequest().authenticated();
                });

        security.authenticationProvider(customAuthenticationProvider);
        security.addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }
}
