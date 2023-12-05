package com.epitech.bankserver.config;

import com.epitech.bankserver.model.account.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
        )
                .formLogin((form) -> form
                        .loginPage("/admin/login")
                        .permitAll()
                        .defaultSuccessUrl("/admin/hello", true)
                )
                .logout((logout) -> logout.permitAll());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        String encodedPassword = passwordEncoder().encode("admin");
//        UserDetails userDetails = User.builder().username("admin").password(encodedPassword).build();
        UserDetails user = User.withUsername("admin")
                .passwordEncoder(password -> passwordEncoder().encode("admin"))
                .password("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
