package org.example.TelegramProject.api.handlers.menu;

import lombok.SneakyThrows;
import org.example.TelegramProject.Bot;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.model.User;
import org.example.TelegramProject.cashe.UserDataCache;
import org.example.TelegramProject.service.UsersProfileDataService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

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
            final User profileData = userDataCache.getUserProfileData(message.getChatId());

            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);
            if (profileData != null) {

                    userReply = new SendMessage(Long.toString(message.getChatId()), bot.getInfo());


            } else {
                userReply = new SendMessage(Long.toString(message.getChatId()), "Данные отсутствуют в БД !");
            }
       return userReply;

        }

        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_USER_PROFILE;
        }
}
