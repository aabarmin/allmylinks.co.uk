package dev.abarmin.aml.backoffice.profile;

import dev.abarmin.aml.backoffice.profile.converter.ProfileChangeRequestToRowConverter;
import dev.abarmin.aml.backoffice.profile.model.ProfileChangeRequestRow;
import dev.abarmin.aml.profile.ProfileChangeRepository;
import dev.abarmin.aml.profile.ProfileChangeRequestService;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static dev.abarmin.aml.config.BasePaths.BACKOFFICE;

@Controller
@RequiredArgsConstructor
@RequestMapping(BACKOFFICE + "/profile/requests")
public class ProfileChangeRequestController {

  private final ProfileChangeRepository repository;
  private final ProfileChangeRequestToRowConverter converter;
  private final ProfileChangeRequestService requestService;

  @GetMapping
  public String profileRequests() {
    return "backoffice/profile-requests";
  }

  @GetMapping("/{id}/process")
  public String process(final @PathVariable("id") long requestId) {
    repository
      .findById(requestId)
      .map(requestService::process);

    return "redirect:" + BACKOFFICE + "/profile/requests";
  }

  @ModelAttribute("model")
  public Page<ProfileChangeRequestRow> getRequestsPage(
    final @RequestParam(value = "page", defaultValue = "0") int page,
    final @RequestParam(value = "status", defaultValue = "CREATED") ProfileChangeStatus status
    ) {
    final PageRequest request = PageRequest.of(page, 50);
    return repository
      .findAllByChangeStatus(status, request)
      .map(converter::convert);
  }

}
