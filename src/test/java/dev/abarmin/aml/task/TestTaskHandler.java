package dev.abarmin.aml.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTaskHandler implements TaskHandler<String> {
  @Override
  public Class<String> getPayloadType() {
    return String.class;
  }

  @Override
  public boolean supports(Task task) {
    return StringUtils.equals(task.getTaskType(), "TEST");
  }

  @Override
  public void handle(String payload) {
    log.info(payload);
  }
}
