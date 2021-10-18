package api;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Определяет обработчики сообщений для каждого состояния

@Component
public class BotStateContext {

        private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

        public BotStateContext(List<InputMessageHandler> messageHandlers) {
            messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
        }

        public SendMessage processInputMessage(BotState currentState, Message message) {
            InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
            return currentMessageHandler.handle(message);
        }

        private InputMessageHandler findMessageHandler(BotState currentState) {
            if (isFillingProfileState(currentState)) {
                return messageHandlers.get(BotState.USER_PROFILE);
            }

            return messageHandlers.get(currentState);
        }

        private boolean isFillingProfileState(BotState currentState) {
            switch (currentState) {
                case ASK_SUM:

                case USER_PROFILE:
                case PROFILE_FILLED:
                    return true;
                default:
                    return false;
            }
        }

    }
