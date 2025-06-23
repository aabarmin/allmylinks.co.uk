package dev.abarmin.aml.sitemap.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Data
@Builder
public class SitemapRecord {
  private final String loc;

  @Builder.Default
  @JsonSerialize(using = SitemapRecordDateSerializer.class)
  private final ZonedDateTime lastmod = ZonedDateTime.now(ZoneOffset.UTC);
}
