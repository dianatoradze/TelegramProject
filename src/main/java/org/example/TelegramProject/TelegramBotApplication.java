package org.example.TelegramProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// чтобы пропустить ошибку Failed to determine suitable jdbc url
//@EnableJpaRepositories("org.example.TelegramProject")
@EnableAutoConfiguration (exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DataSourceProperties.class})
//@EnableJpaRepositories("org.example.TelegramProject.model")
@EntityScan("org.example.TelegramProject.model")
@SpringBootApplication
public class TelegramBotApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

	    SpringApplication.run(TelegramBotApplication.class, args);

	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TelegramBotApplication.class);
	}
//	@Bean
//	public CommandLineRunner demo(UserProfileDataRepository repository) {
//		return new CommandLineRunner() {
//			// начиная со spring boot 1.5 ридонли над методом который вызывает репозиторий нужен обязательно
//			// что бы не закрывалась сессия.
//			@Override
//			@Transactional(readOnly = true)
//			public void run(String... args) throws Exception {
//
//				System.out.println("\n1.findAll()...");
//				for (UserProfileData user : repository.findAll()) {
//					System.out.println(user);
//				}
//
//				System.out.println("Done!");
//			}
//		};
//	}
}
