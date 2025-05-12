package dev.abarmin.aml.home;

import dev.abarmin.aml.registration.PreRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {
  @ModelAttribute("form")
  public PreRegistrationForm preRegistrationForm() {
    return new PreRegistrationForm();
  }

  @GetMapping("/")
  public String home() {
    return "public/home";
  }
}
