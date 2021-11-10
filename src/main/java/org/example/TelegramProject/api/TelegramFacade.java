package org.example.TelegramProject.api;

import org.example.TelegramProject.cashe.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

    @Component
    @Slf4j //для тестирования
    public class TelegramFacade {
        private BotStateContext botStateContext;
        private UserDataCache userDataCache;

        public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache) {
            this.botStateContext = botStateContext;
            this.userDataCache = userDataCache;
        }

        public SendMessage handleUpdate(Update update) {
            SendMessage replyMessage = null;

            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                log.info("New message from User:{}, chatId: {},  with text: {}",
                        message.getFrom().getUserName(), message.getChatId(), message.getText());
                replyMessage = handleInputMessage(message);
            }

            return replyMessage;
        }

        private SendMessage handleInputMessage(Message message) {
            String inputMsg = message.getText();
            @lombok.NonNull Long userId = message.getFrom().getId();
            BotState botState;
            SendMessage replyMessage;

            switch (inputMsg) {
                case "/start":
                    botState = BotState.APART_SEARCH;
                    break;
                case "Получить предложение о поиске":
                    botState = BotState.USER_PROFILE;
                    break;
                case "Помощь":
                    botState = BotState.SHOW_HELP_MENU;
                    break;
                default:
                    botState = userDataCache.getUsersCurrentBotState(userId);
                    break;
            }

            userDataCache.setUsersCurrentBotState(userId, botState);

            replyMessage = botStateContext.processInputMessage(botState, message);

            return replyMessage;
        }
}
