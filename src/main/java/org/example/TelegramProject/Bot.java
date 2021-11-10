package org.example.TelegramProject;

import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.TelegramFacade;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Slf4j
public class Bot extends TelegramLongPollingBot {

    private String botToken="2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName="@testerforhelp_bot";
    private String webHookPath;
    private final TelegramFacade telegramFacade;

    public Bot(String botUserName, String botUserNamem,TelegramFacade telegramFacade) {

        this.telegramFacade = telegramFacade;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
    public String getBotToken() {
        return this.botToken;
    }

    public String getWebHookPath() {
        return this.webHookPath;
    }


    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("TelegramBot onUpdateReceived {}", update);
        SendMessage replyMessageToUser = telegramFacade.handleUpdate(update);

    }




}
