package dev.abarmin.aml.login;

import com.google.common.base.Splitter;
import dev.abarmin.aml.registration.domain.UserRoles;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private static final String SELECT_USER_BY_EMAIL = """
    select
      u.user_email,
      ua.account_password,
      string_agg(ur.role, ', ') as roles
    from users u
    inner join user_accounts ua on
      ua.user_id = u.id
      and ua.account_type = 'USERNAME_PASSWORD'
      and ua.account_is_active = true
    left join user_roles ur on
    	ur.user_id = u.id
    where u.user_email = :user_email
    group by u.user_email, ua.account_password
    """;

  private final JdbcClient jdbcClient;

  private final RowMapper<CustomUserDetails> mapper = (rs, rowNum) -> new CustomUserDetails(
    rs.getString("user_email"),
    rs.getString("account_password"),
    convertRoles(rs.getString("roles"))
  );

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jdbcClient.sql(SELECT_USER_BY_EMAIL)
      .param("user_email", username)
      .query(mapper)
      .optional()
      .orElseThrow(() -> new UsernameNotFoundException(String.format(
        "User with email %s not found",
        username
      )));
  }

  private List<String> convertRoles(String roleString) {
    if (StringUtils.isEmpty(roleString)) {
      return List.of(UserRoles.USER);
    }
    final List<String> databaseRoles = Splitter.on(",")
      .trimResults()
      .omitEmptyStrings()
      .splitToList(roleString);
    final List<String> allRoles = new ArrayList<>(databaseRoles);
    allRoles.add(UserRoles.USER);
    return Collections.unmodifiableList(allRoles);
  }
}
