package dev.abarmin.aml.subscribe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
  private final SubscriptionService service;

  @PostMapping("/subscribe")
  public String subscribe(final @Validated SubscriptionForm subscriptionForm,
                          final BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "public/components-reusable/subscribe-pane::content";
    }

    service.subscribe(subscriptionForm.getEmail(), SubscriptionSource.NEWSLETTER);

    return "public/components-reusable/subscribe-pane ::done";
  }

  @PostMapping("/subscribe-from-pricing")
  public String subscribeFromPricing(final @Validated SubscriptionForm subscriptionForm,
                                     final BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "public/components-pricing/pricing-pane::content";
    }

    service.subscribe(subscriptionForm.getEmail(), SubscriptionSource.PREMIUM_WAIT_LIST);

    return "public/components-pricing/pricing-pane :: done";
  }
}
