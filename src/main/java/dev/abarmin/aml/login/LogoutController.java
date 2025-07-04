package dev.abarmin.aml.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
  SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

  @GetMapping("/private/logout")
  public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
    logoutHandler.logout(request, response, authentication);

    return "redirect:/";
  }
}
