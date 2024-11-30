package dev.abarmin.aml.login;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private static final String SELECT_USER_BY_EMAIL = """
    select
      u.user_email,
      ua.account_password
    from users u
    inner join user_accounts ua on
      ua.user_id = u.id
      and ua.account_type = 'USERNAME_PASSWORD'
      and ua.account_is_active = true
    where u.user_email = :user_email
    """;

  private final JdbcClient jdbcClient;

  private final RowMapper<CustomUserDetails> mapper = (rs, rowNum) -> {
    return new CustomUserDetails(
      rs.getString("user_email"),
      rs.getString("account_password")
    );
  };

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jdbcClient.sql(SELECT_USER_BY_EMAIL)
      .param("user_email", username)
      .query(mapper)
      .optional()
      .orElse(null);
  }
}
