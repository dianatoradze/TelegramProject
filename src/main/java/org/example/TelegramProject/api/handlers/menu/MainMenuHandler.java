package org.example.TelegramProject.api.handlers.menu;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.InputMessageHandler;
import org.example.TelegramProject.service.MainMenuService;
import org.example.TelegramProject.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
@Component
public class MainMenuHandler implements InputMessageHandler {

        private ReplyMessagesService messagesService;
        private MainMenuService mainMenuService;

        public MainMenuHandler(ReplyMessagesService messagesService, MainMenuService mainMenuService) {
            this.messagesService = messagesService;
            this.mainMenuService = mainMenuService;
        }

        @Override
        public SendMessage handle(Message message) {
            return mainMenuService.getMainMenuMessage(String.valueOf(message.getChatId()), messagesService.getReplyText("reply.showMainMenu"));
        }

        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_MAIN_MENU;
        }
}
