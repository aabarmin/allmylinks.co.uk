package dev.abarmin.aml.legal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/legal")
public class LegalController {
    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "public/legal-privacy-policy";
    }

    @GetMapping("/terms-of-service")
    public String termsOfService() {
        return "public/legal-terms-of-service";
    }

    @GetMapping("/cookie-policy")
    public String cookiePolicy() {
        return "public/legal-cookie-policy";
    }
}
