package org.example.TelegramProject.api.handlers.menu;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.cashe.UserDataCache;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ShowProfileHandler implements InputMessageHandler {
        private UserDataCache userDataCache;

    public ShowProfileHandler(UserDataCache userDataCache) {
            this.userDataCache = userDataCache;
        }

        @Override
        public SendMessage handle(Message message) {
            final long userId = Math.toIntExact(message.getFrom().getId());
            final UserProfileData profileData = userDataCache.getUserProfileData(Long.valueOf(userId));

            userDataCache.setUsersCurrentBotState(Long.valueOf(userId), BotState.SHOW_MAIN_MENU);
            return new SendMessage(new Long(message.getChatId()).toString(), String.format("%s%n -------------------%s%nТип квартиры: %d%nСумма аренды: %s%nДата начала аренды: %d%n" +
                            "Дата окончания аренды:%s%n", "Данные по вашим преждложениям", profileData.getApartType(), profileData.getSum()));
        }
            // добавить дату аренды
        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_USER_PROFILE;
        }
}
