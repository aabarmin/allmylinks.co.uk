package dev.abarmin.aml.registration;

import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegistrationFormValidator implements Validator {
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return RegistrationForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    final RegistrationForm form = (RegistrationForm) target;
    userRepository.findByEmail(form.getEmail())
      .ifPresent(user -> errors.rejectValue("email", "email.exists"));
    profileRepository.findByLink(form.getLink())
      .ifPresent(profile -> errors.rejectValue("link", "link.exists"));
    if (!form.isAcceptTerms()) {
      errors.rejectValue("acceptTerms", "acceptTerms.required");
    }
  }
}
