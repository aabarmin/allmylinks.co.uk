package dev.abarmin.aml.registration;

import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PreRegistrationFormValidator implements Validator {
  private final UserRepository userRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return PreRegistrationForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    final PreRegistrationForm form = (PreRegistrationForm) target;
    userRepository.findByEmail(form.getEmail())
      .ifPresent(user -> errors.rejectValue("email", "email.exists"));
  }
}
