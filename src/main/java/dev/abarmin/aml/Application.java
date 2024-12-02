package dev.abarmin.aml;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.file.DiskStorageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
  DiskStorageConfiguration.class,
  AppConfiguration.class
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
