package dev.abarmin.aml.registration;

import dev.abarmin.aml.registration.domain.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
  private final RegistrationFormValidator formValidator;
  private final RegistrationService registrationService;

  @ModelAttribute("form")
  public RegistrationForm registrationForm(@RequestParam(value = "link", required = false) String link) {
    final RegistrationForm form = new RegistrationForm();
    form.setLink(link);
    return form;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(formValidator);
  }

  @GetMapping("/register")
  public String registration() {
    return "public/registration";
  }

  @PostMapping(value = "/register", params = {"preRegistration", "email"})
  public String preRegistration(@RequestParam("email") String email,
                                Model model) {

    final RegistrationForm form = RegistrationForm.builder()
      .email(email)
      .build();

    model.addAttribute("form", form);

    return "public/registration";
  }

  @PostMapping("/register")
  public String completeRegistration(@Validated @ModelAttribute("form") RegistrationForm form,
                                     BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "public/components-registration/registration-pane :: registration-form";
    }

    registrationService.register(form, AccountType.USERNAME_PASSWORD);

    return "public/components-registration/registration-pane :: registration-done";
  }
}
