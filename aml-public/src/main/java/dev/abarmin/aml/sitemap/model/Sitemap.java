package dev.abarmin.aml.sitemap.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@JacksonXmlRootElement(localName = "sitemapindex")
public class Sitemap {
  @Builder.Default
  @JacksonXmlProperty(isAttribute = true)
  private final String xmlns = "http://www.sitemaps.org/schemas/sitemap/0.9";

  @Singular
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "sitemap")
  private List<SitemapRecord> records;
}
