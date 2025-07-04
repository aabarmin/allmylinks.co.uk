package dev.abarmin.aml.cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import static dev.abarmin.aml.cookies.CookieControllerAdvice.COOKIE_NAME;

@Controller
public class CookieController {
  @PostMapping("/cookies/accept")
  public String acceptCookies(HttpServletResponse response) {

    final Cookie cookie = new Cookie(COOKIE_NAME, "true");
    cookie.setPath("/");
    response.addCookie(cookie);

    return "public/components-reusable/cookies-banner :: accepted";
  }
}
