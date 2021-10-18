package api.handlers;

import api.BotState;
import api.InputMessageHandler;
import cashe.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.ReplyMessagesService;

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
        return BotState.ASK_SUM;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId, "reply.askSum");
        userDataCache.setUsersCurrentBotState(userId, BotState.USER_PROFILE);

        return replyToUser;
    }


}
