package org.example.TelegramProject.api;

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
//            if (isFillingProfileState(currentState)) {
//                return messageHandlers.get(BotState.USER_PROFILE);//filling_profile
//            }

            return messageHandlers.get(currentState);
        }

        private boolean isFillingProfileState(BotState currentState) {
            switch (currentState) {
                case APART_SEARCH:
                case ASK_SUM:

                case ASK_DATE_BEGIN:
                case ASK_DATE_FINISH:

                case APART_SEARCH_STARTED:
                case APART_INFO_RESPONCE_AWAITING:
                case APART_SEARCH_FINISH:

                case USER_PROFILE:
                case PROFILE_FILLED:
                    return true;
                default:
                    return false;
            }
        }

    }
