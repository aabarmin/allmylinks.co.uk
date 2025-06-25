package dev.abarmin.aml.config;

import dev.abarmin.aml.login.CustomUserDetailsService;
import dev.abarmin.aml.registration.oauth2.RegisterOnSuccessOAuth2LoginHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
                                         RegisterOnSuccessOAuth2LoginHandler successOAuth2LoginHandler) throws Exception {
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
        .requestMatchers("/robots.txt").permitAll()
        .requestMatchers("/sitemap.xml").permitAll()
        .requestMatchers("/", "/error").permitAll()
        .requestMatchers("/webjars/**").permitAll()
        .requestMatchers("/img/**").permitAll()
        .requestMatchers("/css/**").permitAll()
        .requestMatchers("/js/**").permitAll()
        .requestMatchers("/oauth2/**").permitAll()
        .requestMatchers("/actuator/**").permitAll()

        .requestMatchers("/pricing").permitAll()
        .requestMatchers("/legal/**").permitAll()
        .requestMatchers("/l/**").permitAll()
        .requestMatchers("/file/**").permitAll()

        .requestMatchers("/login", "/register", "/register/pre").permitAll()
        .requestMatchers("/subscribe", "/subscribe-from-pricing").permitAll()
        .requestMatchers("/cookies/accept").permitAll()

        .requestMatchers("/register/oidc").hasAuthority("OIDC_USER")
        .requestMatchers("/private/**").hasRole("USER")
        .requestMatchers("/backoffice/**").hasRole("BACKOFFICE")

        .anyRequest().authenticated())
      .oauth2Login(oauth2 -> oauth2
        .loginPage("/login")
        .successHandler(successOAuth2LoginHandler)
      )
      .build();
  }

  @Bean
  public GrantedAuthoritiesMapper authoritiesMapper() {
    return new GrantedAuthoritiesMapper() {
      @Override
      public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        final Collection<GrantedAuthority> finalAuthorities = new ArrayList<>(authorities);
        finalAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // todo, if the user has admin permissions add ROLE_BACKOFFICE
        return finalAuthorities;
      }
    };
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
