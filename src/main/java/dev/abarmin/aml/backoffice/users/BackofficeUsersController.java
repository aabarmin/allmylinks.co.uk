package dev.abarmin.aml.backoffice.users;

import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BackofficeUsersController {
  private final UserRepository userRepository;
  private final ProfileHelper profileHelper;

  @ModelAttribute("profileHelper")
  public ProfileHelper profileHelper() {
    return profileHelper;
  }

  @ModelAttribute("model")
  public Page<User> model(@RequestParam(value = "page", defaultValue = "0") int page) {
    final PageRequest request = PageRequest
      .of(page, 50)
      .withSort(Sort.by(Sort.Direction.ASC, "email"));

    return userRepository.findAll(request);
  }

  @GetMapping("/backoffice/users")
  public String backofficeUsers() {
    return "backoffice/users";
  }
}
