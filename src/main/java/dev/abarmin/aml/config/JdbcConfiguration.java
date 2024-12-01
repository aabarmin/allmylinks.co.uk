package dev.abarmin.aml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.converter.BlockPropsConverter;
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
      new BlockPropsConverter.Writer(objectMapper)
    );
  }
}
