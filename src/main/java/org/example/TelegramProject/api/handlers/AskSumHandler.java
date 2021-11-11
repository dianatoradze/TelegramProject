package org.example.TelegramProject.api.handlers;

import lombok.NonNull;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.cashe.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.example.TelegramProject.service.ReplyMessagesService;

//Спрашивает пользователя- хочет ли он получить предложение о поиске аренды
@Slf4j
@Component
public class AskSumHandler implements InputMessageHandler {

    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public AskSumHandler(UserDataCache userDataCache,
                         ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
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
        @NonNull Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askApart");
        userDataCache.setUsersCurrentBotState(userId, BotState.USER_PROFILE); //filling_profile

        return replyToUser;
    }


}
