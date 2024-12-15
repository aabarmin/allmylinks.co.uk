package dev.abarmin.aml.registration.domain;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@UtilityClass
public class UserRoles {
  public static final String USER = "ROLE_USER";
  public static final String BACKOFFICE = "ROLE_BACKOFFICE";

  public static final SimpleGrantedAuthority BACKOFFICE_AUTHORITY = new SimpleGrantedAuthority(BACKOFFICE);
}
