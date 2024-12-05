package dev.abarmin.aml.registration;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PreRegistrationController {
  private final PreRegistrationFormValidator formValidator;

  @ModelAttribute("form")
  public PreRegistrationForm preRegistrationForm() {
    return new PreRegistrationForm();
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(formValidator);
  }

  @PostMapping("/register/pre")
  public String trySignUp(@Valid @ModelAttribute("form") PreRegistrationForm form,
                          BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      return "redirect:/register?email=" + form.getEmail();
    }

    return "public/components-home/landing-area :: landing-area-left";
  }
}
