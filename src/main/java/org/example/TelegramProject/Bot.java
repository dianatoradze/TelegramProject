package org.example.TelegramProject;

import api.TelegramFacade;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Slf4j
public class Bot extends TelegramLongPollingBot {

    @Autowired(required=true)
    private String botToken = "2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName = "@testerforhelp_bot";
    private TelegramFacade telegramFacade;
    public Bot (TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        log.info("TelegramBot onUpdateReceived {}", update);
            SendMessage replyMessageToUser = telegramFacade.handleUpdate(update);
        sendMessage(replyMessageToUser);

    }
    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message " + e.getMessage());
        }
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

}
