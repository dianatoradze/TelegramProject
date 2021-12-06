package org.example.TelegramProject.api.handlers.menu;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.example.TelegramProject.Bot;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.model.ApartEntity;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.cashe.UserDataCache;
import org.example.TelegramProject.service.UsersProfileDataService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Map;

@Component
public class ShowProfileHandler implements InputMessageHandler {
        private UserDataCache userDataCache;
        private UsersProfileDataService usersProfileDataService;
        private Bot bot;


    public ShowProfileHandler(UserDataCache userDataCache, @Lazy Bot bot, UsersProfileDataService usersProfileDataService) {
            this.userDataCache = userDataCache;
            this.usersProfileDataService = usersProfileDataService;
            this.bot = bot;
    }

        @SneakyThrows
        @Override
        public SendMessage handle(Message message) {
            SendMessage userReply;

            Long userId = message.getFrom().getId();
            //usersProfileDataService
            final UserProfileData profileData = userDataCache.getUserProfileData(message.getChatId());

            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);
            if (profileData != null) {

                    userReply = new SendMessage(Long.toString(message.getChatId()), bot.getInfo());
//                userReply = new SendMessage(Long.toString(message.getChatId()),
//                        String.format("Данные по вашему поиску:", profileData.toString()));

            } else {
                userReply = new SendMessage(Long.toString(message.getChatId()), "Данные отсутствуют в БД !");
            }
       return userReply;

        }
//             добавить дату аренды
        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_USER_PROFILE;
        }
}
