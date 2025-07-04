package dev.abarmin.aml.backoffice.dashboard;

import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class BackofficeHomeController {
  private final UserRepository userRepository;

  @ModelAttribute("model")
  public BackofficeDashboard model() {
    return BackofficeDashboard.builder()
      .totalUsers(userRepository.count())
      .build();
  }

  @GetMapping("/backoffice")
  public String backofficeHome() {
    return "backoffice/home";
  }
}
