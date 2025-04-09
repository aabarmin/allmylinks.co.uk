package dev.abarmin.aml.sitemap;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.sitemap.model.Sitemap;
import dev.abarmin.aml.sitemap.model.SitemapRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/sitemap.xml")
public class SitemapController {

  private static final ZonedDateTime DEFAULT_MODIFICATION_TIME = ZonedDateTime.of(
    LocalDateTime.of(2025, Month.APRIL, 1, 10, 0, 0),
    ZoneOffset.UTC);

  private final XmlMapper xmlMapper;
  private final AppConfiguration configuration;

  public SitemapController(AppConfiguration configuration) {
    this.configuration = configuration;
    this.xmlMapper = new XmlMapper();
    xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    xmlMapper.findAndRegisterModules();
  }

  @GetMapping
  public String sitemap() throws Exception {
    return xmlMapper
      .writerWithDefaultPrettyPrinter()
      .writeValueAsString(Sitemap.builder()
      .record(forPage("/"))
      .record(forPage("/pricing"))
      .record(forPage("/legal/cookie-policy"))
      .record(forPage("/legal/privacy-policy"))
      .record(forPage("/legal/terms-of-service"))
      .build());
  }

  private SitemapRecord forPage(final String page) {
    final String baseUrl;
    if (StringUtils.endsWith(configuration.getBaseUrl(), "/")) {
      baseUrl = StringUtils.chop(configuration.getBaseUrl());
    } else {
      baseUrl = configuration.getBaseUrl();
    }

    return SitemapRecord.builder()
      .loc(baseUrl + page)
      .lastmod(DEFAULT_MODIFICATION_TIME)
      .build();
  }
}
