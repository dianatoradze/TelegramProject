package org.example.TelegramProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
// чтобы пропустить ошибку Failed to determine suitable jdbc url
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
// чтобы пропустить ошибку Consider defining a bean of type
@ComponentScan({"com.delivery.service","com.delivery.request"})
@SpringBootApplication
public class TelegramBotApplication  {

	public static void main(String[] args) {

		ApiContextInitializer.init();
	    SpringApplication.run(TelegramBotApplication.class, args);

	}

}
