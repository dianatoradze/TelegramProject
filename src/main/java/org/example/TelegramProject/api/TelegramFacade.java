package org.example.TelegramProject.api;

import lombok.SneakyThrows;
import org.example.TelegramProject.Bot;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.cashe.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.service.MainMenuService;
import org.example.TelegramProject.service.ReplyMessagesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Component
@Slf4j //для тестирования
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MainMenuService mainMenuService;
    private Bot myBot;
    private ReplyMessagesService messagesService;

    // проверить конструктор
    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache,
                          MainMenuService mainMenuService, @Lazy Bot myBot, ReplyMessagesService messagesService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.mainMenuService = mainMenuService;
        this.myBot = myBot;
        this.messagesService = messagesService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());

            return processCallbackQuery(callbackQuery);
        }
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        long chatId = message.getChatId();
        long userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage = null;

        switch (inputMsg) {
            case "/start":
                botState = BotState.APART_SEARCH;
                break;
            case "Выполнить поиск аренды?":
                botState = BotState.FILLING_PROFILE; // проверить состояние
                break;
            case "Мои варианты":
                myBot.getInfo(replyMessage);
                botState = BotState.SHOW_USER_PROFILE;
                break;
            case "Помощь":
                botState = BotState.SHOW_HELP_MENU;
                break;

            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);
        // обработать входящение сообщение из контекста
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = String.valueOf(buttonQuery.getMessage().getChatId());
        final String userId = String.valueOf(buttonQuery.getFrom().getId());
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(Long.parseLong(chatId), "Воспользуйтесь главным меню");

        //выбор кнопок
        if (buttonQuery.getData().equals("buttonYes")) {
            callBackAnswer = new SendMessage(chatId, "Введи минимальную сумму аренды");
            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.ASK_TYPE_APART);
        } else if (buttonQuery.getData().equals("buttonNo")) {
            callBackAnswer = sendAnswerCallbackQuery("Возвращайся позже", true, buttonQuery);
        }       //проверить параметр false/true

        //Выбор типа квартиры
        else if (buttonQuery.getData().equals("buttonTypeOne")) {
            UserProfileData userProfileData = userDataCache.getUserProfileData(Long.valueOf(userId));
            userProfileData.setApartOneRoom("Однокомнатная");
            userDataCache.saveUserProfileData(Long.valueOf(userId), userProfileData);
            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.ASK_DATE_BEGIN);
            callBackAnswer = new SendMessage(chatId, "Рассматриваете вариант с комиссией?");
        } else if (buttonQuery.getData().equals("buttonTypeTwo")) {
            UserProfileData userProfileData = userDataCache.getUserProfileData(Long.valueOf(userId));
            userProfileData.setApartTwoRoom("Двухкомнатная");
            //userDataCache.saveUserProfileData(Long.valueOf(userId), userProfileData);
            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.ASK_DATE_BEGIN);
            callBackAnswer = new SendMessage(chatId, "Рассматриваете вариант с комиссией?");

        } else {
            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.SHOW_MAIN_MENU);
        }

        return callBackAnswer;

    }

    //объект ответа на запрос - всплывающие уведомления
    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }

}

