package org.example.TelegramProject.api.handlers.menu;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.cashe.UserDataCache;
import org.example.TelegramProject.service.UsersProfileDataService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ShowProfileHandler implements InputMessageHandler {
        private UserDataCache userDataCache;
        private UsersProfileDataService usersProfileDataService;

    public ShowProfileHandler(UserDataCache userDataCache, UsersProfileDataService usersProfileDataService) {
            this.userDataCache = userDataCache;
            this.usersProfileDataService = usersProfileDataService;
        }

        @Override
        public SendMessage handle(Message message) {
            SendMessage userReply;

            final long userId = Math.toIntExact(message.getFrom().getId());
            final UserProfileData profileData = usersProfileDataService.getUserProfileData(Long.valueOf(message.getChatId()));

            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.SHOW_MAIN_MENU);
            if (profileData != null) {
                userReply = new SendMessage(Long.toString(message.getChatId()),
                        String.format("%s%n-------------------%n%s", "Данные по вашему поиску:", profileData.toString()));
            } else {
                userReply = new SendMessage(Long.toString(message.getChatId()), "Данные отсутствуют в БД !");
            }
//            return new SendMessage(Long.toString(message.getChatId()), String.format("%s%n -------------------%s%nТип квартиры: %d%nСумма аренды: %s%nДата начала аренды: %d%n" +
//                            "Дата окончания аренды:%s%n", "Данные по вашим предложениям", profileData.getApartType(), profileData.getSum()));
       return userReply;

        }
//             добавить дату аренды
        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_USER_PROFILE;
        }
}
