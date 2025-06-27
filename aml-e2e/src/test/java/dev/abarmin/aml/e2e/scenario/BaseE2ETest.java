package dev.abarmin.aml.e2e.scenario;

import dev.abarmin.aml.Application;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
  classes = Application.class,
  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public abstract class BaseE2ETest {
}
