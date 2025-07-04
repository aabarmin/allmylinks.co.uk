package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.PageModel;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/private/dashboard/{pageId}")
public class PageUpdateHandler {
  private final PageRepository pageRepository;

  @PostMapping
  public String updatePage(@PathVariable("pageId") long pageId,
                           @Valid @ModelAttribute("currentPage") PageModel pageModel,
                           BindingResult bindingResult) {

    final Page page = pageRepository.findById(pageModel.getPageId())
      .orElseThrow()
      .withProps(pageModel.getPageProps());

    final Page savedPage = pageRepository.save(page);
    checkArgument(Objects.nonNull(savedPage), "Page was not saved");

    return "redirect:/private/dashboard/" + pageId;
  }
}
