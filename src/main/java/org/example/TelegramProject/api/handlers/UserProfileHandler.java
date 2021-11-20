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
        Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.APART_SEARCH)) {
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askApart");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SUM); // следующее состояние
        }
        if (botState.equals(BotState.ASK_SUM)) {

            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askSum");

            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_DATE_BEGIN);
        }
        if (botState.equals(BotState.ASK_DATE_BEGIN)) {
            profileData.setSum(usersAnswer);

            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataBegin");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_DATE_FINISH);
        }
        if (botState.equals(BotState.ASK_DATE_FINISH)) {
            //profileData.setDateBegin(usersAnswer);

            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataFinish");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_TYPE_APART);
        }
        if (botState.equals(BotState.ASK_TYPE_APART)) {
            //profileData.setDateFinishReceived(usersAnswer);
            profileData.setApartType(String.valueOf(usersAnswer));
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askTypeApart");
            userDataCache.setUsersCurrentBotState(userId, BotState.USER_PROFILE);//profile_field
        }

        if (botState.equals(BotState.USER_PROFILE)) {
            profileData.setApartType(String.valueOf(usersAnswer));

            userDataCache.setUsersCurrentBotState(userId, BotState.APART_SEARCH); // следующее состояние
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.profileFilled");
        }


        userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }


}
