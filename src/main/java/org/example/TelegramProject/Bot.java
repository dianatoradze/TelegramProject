package org.example.TelegramProject;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.TelegramFacade;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Getter
@Setter
@Slf4j
public class Bot extends TelegramLongPollingBot{

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
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            log.info("TelegramBot onUpdateReceived {}", update);

            final BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);
            sendMessage(replyMessageToUser);
        }
        // дописать
      else if (update.hasCallbackQuery()){
            handleCallback(update.getCallbackQuery());
        }

    }
    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {

            Message message = callbackQuery.getMessage();
            String data = callbackQuery.getData();

    }

    private void sendMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message " + e.getMessage());
        }
    }

}
