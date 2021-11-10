package org.example.TelegramProject.api;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

//Обработчик сообщений
public interface InputMessageHandler {

    SendMessage handle(Message message);

    BotState getHandlerName();
}

