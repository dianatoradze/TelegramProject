package org.example.TelegramProject;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramWebhookBot {


    private String botToken = "2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName = "@testerforhelp_bot";
    private String webHookPatch = "http://a71a-2a00-1fa2-403-47e6-6909-34eb-21f4-1b95.ngrok.io";

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId();

        if (update.getMessage() != null && update.getMessage().hasText()) {

            try {
                execute(new SendMessage(chat_id, "Hi" + " " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPatch;
    }

    private String input(String text) {
        if (text.contains("Hi") || text.contains("Hello") || text.contains("Привет")) {
            return "Привет, друг";
        }

        return text;
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
