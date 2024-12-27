package dev.abarmin.aml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.converter.BlockPropsConverter;
import dev.abarmin.aml.dashboard.converter.PagePropsConverter;
import dev.abarmin.aml.profile.converter.ProfileChangePayloadConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JdbcConfiguration extends AbstractJdbcConfiguration {
  private final ObjectMapper objectMapper;

  @Override
  protected List<?> userConverters() {
    return List.of(
      new BlockPropsConverter.Reader(objectMapper),
      new BlockPropsConverter.Writer(objectMapper),

      new PagePropsConverter.Reader(objectMapper),
      new PagePropsConverter.Writer(objectMapper),

      new ProfileChangePayloadConverter.Reader(objectMapper),
      new ProfileChangePayloadConverter.Writer(objectMapper)
    );
  }
}
