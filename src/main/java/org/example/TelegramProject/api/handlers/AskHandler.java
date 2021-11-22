package org.example.TelegramProject.api.handlers;

import lombok.SneakyThrows;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.telegram.telegrambots.meta.api.objects.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.example.TelegramProject.service.ReplyMessagesService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

//Спрашивает пользователя - хочет ли он получить предложение о поиске аренды
@Slf4j
@Component
public class AskHandler implements InputMessageHandler {

    //private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public AskHandler(ReplyMessagesService messagesService) {
        //this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.APART_SEARCH;
    }

    private SendMessage processUsersInput(Message inputMsg) {

        //Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askApart");

        replyToUser.setReplyMarkup(getInlineMessageButtons());

       return replyToUser;
    }
    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // по нажатию передает данные
        InlineKeyboardButton buttonYes = new InlineKeyboardButton();

        InlineKeyboardButton buttonNo = new InlineKeyboardButton();

        buttonYes.setText("Да");
        buttonNo.setText("Нет, спасибо");
        //Каждая кнопка через callBackData, или не работает
        buttonYes.setCallbackData("buttonYes");
        buttonNo.setCallbackData("buttonNo");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonYes);
        keyboardButtonsRow1.add(buttonNo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

}
