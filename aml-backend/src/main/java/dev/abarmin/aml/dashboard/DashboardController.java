package dev.abarmin.aml.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping({
  DashboardController.DASHBOARD_ENDPOINT,
  "/private/dashboard/",
  "/private/dashboard/{pageId}",
  "/private/dashboard/{pageId}/blocks/{blockId}"
})
public class DashboardController {
  public static final String DASHBOARD_ENDPOINT = "/private/dashboard";

  @GetMapping
  public String home() {
    return "private/dashboard";
  }
}
