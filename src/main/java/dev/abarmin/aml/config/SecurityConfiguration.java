package dev.abarmin.aml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/").permitAll()
        .requestMatchers("/webjars/**").permitAll()
        .requestMatchers("/img/**").permitAll()
        .requestMatchers("/css/**").permitAll()

        .requestMatchers("/pricing").permitAll()
        .requestMatchers("/legal/**").permitAll()

        .requestMatchers("/subscribe", "/subscribe-from-pricing").permitAll()

        .anyRequest().authenticated())
      .build();
  }
}
