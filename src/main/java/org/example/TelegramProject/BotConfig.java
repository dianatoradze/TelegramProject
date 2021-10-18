package org.example.TelegramProject;

import api.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String botToken;
    private String botUserName;

    @Bean
    public Bot MyTelegramBot(TelegramFacade telegramFacade)  {

        Bot bot = new Bot(telegramFacade);

        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);

        return bot;
    }
    @Bean
    public MessageSource messageSource() {

            ReloadableResourceBundleMessageSource messageSource =
                    new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;

    }


}
