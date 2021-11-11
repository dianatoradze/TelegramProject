package org.example.TelegramProject;

import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.TelegramFacade;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class Bot extends TelegramWebhookBot {

    private String botToken="2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName="@testerforhelp_bot";
    private String webHookPath="https://21b0-2a00-1fa2-279-4176-b153-358a-e9ab-8a37.ngrok.io";
    private final TelegramFacade telegramFacade;

    public Bot(TelegramFacade telegramFacade) {

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
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.info("TelegramBot onWebhookUpdateReceived {}", update);
        SendMessage replyMessageToUser = telegramFacade.handleUpdate(update);

        return replyMessageToUser;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
