package api;

import cashe.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
    @Slf4j //для тестирования
    public class TelegramFacade {

        private BotStateContext botStateContext;
        private UserDataCache userDataCache;

        public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache) {
            this.botStateContext = botStateContext;
            this.userDataCache = userDataCache;
        }

        public SendMessage handleUpdate(Update update) throws TelegramApiException {
            SendMessage replyMessage = null;
            try {
                Message message = update.getMessage();
                if (message != null && message.hasText()) {
                    //поменять
                    log.info("New message from User:{}, chatId: {},  with text: {}",
                            message.getFrom().getUserName(), message.getChatId(), message.getText());
                    replyMessage = handleInputMessage(message);
                }

            } catch (TelegramApiException e) {
        e.printStackTrace();
    }
            return replyMessage;
        }

        private SendMessage handleInputMessage(Message message) throws TelegramApiException {

            String inputMsg = message.getText();
            int userId = message.getFrom().getId();
            BotState botState;
            SendMessage replyMessage;

            switch (inputMsg) {
                case "/start":
                    botState = BotState.ASK_SUM;
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
