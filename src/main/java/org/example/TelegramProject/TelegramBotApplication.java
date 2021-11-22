package org.example.TelegramProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// чтобы пропустить ошибку Failed to determine suitable jdbc url
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@SpringBootApplication
public class TelegramBotApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		//ApiContextInitializer.init();
	    SpringApplication.run(TelegramBotApplication.class, args);

	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TelegramBotApplication.class);
	}

}