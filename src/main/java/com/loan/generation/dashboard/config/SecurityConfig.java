package com.loan.generation.dashboard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    /*
     * In memory authentication
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry ->
                {
                    registry.requestMatchers("/loan-dashboard/get-status").permitAll();
                    registry.requestMatchers("/loan-dashboard/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/loan-dashboard/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
                }).formLogin(AbstractAuthenticationFilterConfigurer::permitAll)//.formLogin(formLogin -> formLogin.permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("gc")
                .password("$2a$12$DuS4WFz7F/7R9DSxCgWKbelorARVF5BILYcq3ihtabe3MsqdXmb3K")//1234
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password("$2a$12$gGvrqK//2OxELDGzd8kJYOzNDWv2T/AwyudhKu1ez2502g0jXkfRS")//9876
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
