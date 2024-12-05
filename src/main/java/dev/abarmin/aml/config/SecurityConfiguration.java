package dev.abarmin.aml.config;

import dev.abarmin.aml.login.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .formLogin(form -> form
        .loginPage("/login")
        .usernameParameter("email")
        .permitAll())
      .logout(logout -> logout
        .logoutUrl("/private/logout")
        .logoutSuccessUrl("/")
        .permitAll())
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/", "/error").permitAll()
        .requestMatchers("/webjars/**").permitAll()
        .requestMatchers("/img/**").permitAll()
        .requestMatchers("/css/**").permitAll()

        .requestMatchers("/pricing").permitAll()
        .requestMatchers("/legal/**").permitAll()
        .requestMatchers("/l/**").permitAll()
        .requestMatchers("/file/**").permitAll()

        .requestMatchers("/login", "/register", "/register/pre").permitAll()
        .requestMatchers("/subscribe", "/subscribe-from-pricing").permitAll()

        .requestMatchers("/private/**").hasRole("USER")

        .anyRequest().authenticated())
      .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(JdbcClient jdbcClient) {
    return new CustomUserDetailsService(jdbcClient);
  }
}
