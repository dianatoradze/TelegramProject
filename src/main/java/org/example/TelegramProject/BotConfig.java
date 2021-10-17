package org.example.TelegramProject;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    private String webHookPatch;

    @Bean
    public Bot MyTelegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        Bot bot = new Bot();

        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);


        return bot;
    }
}