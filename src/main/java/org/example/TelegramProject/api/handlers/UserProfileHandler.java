package org.example.TelegramProject.api.handlers;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.cashe.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.example.TelegramProject.service.ReplyMessagesService;

import java.util.Date;

// Кэш в памяти usersBotStates: user_id и состояние бота пользователя
// usersProfileData: user_id и данные профиля пользователя

public class UserProfileHandler implements InputMessageHandler {

    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public UserProfileHandler(UserDataCache userDataCache,
                              ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.USER_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.APART_SEARCH);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.USER_PROFILE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int usersAnswer = Integer.parseInt(inputMsg.getText());
        @lombok.NonNull Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.APART_SEARCH)) {
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askApart");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SUM_BEGIN); // следующее состояние
        }
        if (botState.equals(BotState.ASK_SUM_BEGIN)) {
            profileData.setSumBegin(usersAnswer);
                    replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askSumBegin");

            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SUM_FINISH);
            // replyToUser = new SendMessage(chatId, String.format("%s %s", "Начальная сумма поиска", apartData));
        }
        if (botState.equals(BotState.ASK_SUM_FINISH)) {
            profileData.setSumFinish(usersAnswer);
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askSumFinish");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_DATE_BEGIN);
        }
        if (botState.equals(BotState.ASK_DATE_BEGIN)) {
            //profileData.setDateBegin(usersAnswer);
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataBegin");
            userDataCache.setUsersCurrentBotState(userId, BotState.DATE_FINISH_RECEIVED);
        }
        if (botState.equals(BotState.DATE_FINISH_RECEIVED)) {
            //profileData.setDateFinishReceived(usersAnswer);
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataFinish");
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);//profile_field
        }
        if (botState.equals(BotState.SHOW_MAIN_MENU)) {
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.showMainMenu");
            userDataCache.setUsersCurrentBotState(userId, BotState.APART_SEARCH); // следующее состояние
        }


        userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }


}
