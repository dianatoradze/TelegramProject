package org.example.TelegramProject;

import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.TelegramFacade;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "telegrambot")

public class BotConfig {

    private String botToken="2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName="@testerforhelp_bot";
    private String webHookPatch="https://21b0-2a00-1fa2-279-4176-b153-358a-e9ab-8a37.ngrok.io";

    @Bean
    public Bot myTelegramBot(TelegramFacade telegramFacade) throws TelegramApiException {
        Bot bot = new Bot(telegramFacade);

        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setWebHookPath(webHookPatch);
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


    public String getBotToken() {
        return this.botToken;
    }

    public String getBotUserName() {
        return this.botUserName;
    }

    public String getWebHookPatch() {
        return this.webHookPatch;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setWebHookPatch(String webHookPatch) {
        this.webHookPatch = webHookPatch;
    }
}
