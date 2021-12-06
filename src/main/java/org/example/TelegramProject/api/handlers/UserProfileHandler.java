package org.example.TelegramProject.api.handlers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.cashe.UserDataCache;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.service.UsersProfileDataService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.example.TelegramProject.service.ReplyMessagesService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Кэш в памяти usersBotStates: userid и состояние бота пользователя
// usersProfileData: user_id и данные профиля пользователя
@Slf4j
@Component
public class UserProfileHandler implements InputMessageHandler {

    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;
    private UsersProfileDataService profileDataService;

    public UserProfileHandler(UserDataCache userDataCache,
                              ReplyMessagesService messagesService, UsersProfileDataService profileDataService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.profileDataService = profileDataService;

    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.USER_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_SUM);//проверить
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.USER_PROFILE;
    }

    @SneakyThrows
    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = String.valueOf(inputMsg.getText());
        Long userId = inputMsg.getFrom().getId();                                                                                                                           userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM");

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_SUM)) {
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askSum");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_TYPE_APART); // следующее состояние
        }
        if (botState.equals(BotState.ASK_TYPE_APART)) {
            profileData.setSum(String.valueOf(usersAnswer));
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askTypeApart");
            replyToUser.setReplyMarkup(getApartTypeButtonsMarkup());
        }

        if (botState.equals(BotState.ASK_DATE_BEGIN)) {
            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataBegin");
            profileData.setApartType(String.valueOf(usersAnswer));
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_DATE_FINISH);

        }
        if (botState.equals(BotState.ASK_DATE_FINISH)) {

            replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "reply.askDataFinish");
            profileData.setDateBegin(usersAnswer);

            userDataCache.setUsersCurrentBotState(userId, BotState.PROFILE_FILLED);
        }
        if (botState.equals(BotState.PROFILE_FILLED)) {
            profileData.setDateFinish(usersAnswer);
            profileData.setChatId(chatId);
            //profileDataService.saveUserProfileData(profileData);
            userDataCache.setUsersCurrentBotState(userId, BotState.APART_SEARCH); // SHOW_MAIN_MENU
            //String profileFilledMessage = messagesService.getReplyMessage(String.valueOf(chatId),profileData);
            //replyToUser = messagesService.getReplyMessage(String.valueOf(chatId), "Данные по вашей поиску ", profileData);
            replyToUser = new SendMessage(String.valueOf(chatId), String.format("%s %s", "Данные по вашей поиску",profileData));

        }

            userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }

    private InlineKeyboardMarkup getApartTypeButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonTypeOne = new InlineKeyboardButton();
        buttonTypeOne.setText("1к");
        InlineKeyboardButton buttonTypeTwo = new InlineKeyboardButton();
        buttonTypeTwo.setText("2к");

        buttonTypeOne.setCallbackData("buttonTypeOne");
        buttonTypeTwo.setCallbackData("buttonTypeTwo");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonTypeOne);
        keyboardButtonsRow1.add(buttonTypeTwo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
    }


}
