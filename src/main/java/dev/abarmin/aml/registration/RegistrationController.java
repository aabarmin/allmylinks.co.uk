package dev.abarmin.aml.registration;

import dev.abarmin.aml.registration.domain.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
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

import static com.google.common.base.Preconditions.checkArgument;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
  private final RegistrationFormValidator formValidator;
  private final RegistrationService registrationService;

  @ModelAttribute("form")
  public RegistrationForm registrationForm(@RequestParam(value = "link", required = false) String link,
                                           Authentication authentication) {
    final RegistrationForm form = new RegistrationForm();
    form.setLink(link);

    if (RegistrationHelper.isOAuth2User(authentication)) {
      form.setEmailEditable(false);
      form.setPasswordRequired(false);
    }

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

  @GetMapping("/register/oidc")
  public String oidcRegistration(Model model,
                                 Authentication authentication) {
    final Object principalCandidate = authentication.getPrincipal();

    checkArgument(
      principalCandidate instanceof DefaultOidcUser,
      "Invalid principal type");

    final DefaultOidcUser principal = (DefaultOidcUser) principalCandidate;
    final RegistrationForm form = RegistrationForm.builder()
      .emailEditable(false)
      .passwordRequired(false)
      .email(principal.getEmail())
      .name(principal.getAttribute("name"))
      .build();

    model.addAttribute("form", form);

    return "public/registration";
  }

  @PostMapping("/register")
  public String completeRegistration(@Validated @ModelAttribute("form") RegistrationForm form,
                                     BindingResult bindingResult,
                                     Authentication authentication) {

    if (bindingResult.hasErrors()) {
      return "public/components-registration/registration-pane :: registration-form";
    }

    final AccountType accountType;
    if (authentication == null) {
      accountType = AccountType.USERNAME_PASSWORD;
    } else if (authentication.getPrincipal() instanceof DefaultOidcUser) {
      accountType = AccountType.OIDC;
    } else {
      throw new IllegalStateException("Unsupported authentication type " + authentication.getClass());
    }

    registrationService.register(form, accountType);

    return "public/components-registration/registration-pane :: registration-done";
  }
}
