package org.example.TelegramProject.service;

import org.example.TelegramProject.model.ApartEntity;
import org.example.TelegramProject.parcer.Apart;

public class InfoService {
    private ReplyMessagesService messagesService;
    private ApartEntity apart;
     Apart apartParse;

    public InfoService(ReplyMessagesService messagesService) {
        this.messagesService = messagesService;
    }

    public String getInfo() {
        String info =
                "\nАдрес" + apart.getAdress()
                        + "\nКоличество комнат" + apart.getApartType()
                        + "\n\nОписание\n" + apart.getDescription()
                        + "\n\nОписание\n" + apart.getSum()
                        + "\n\nВремя размещения объявления " + apartParse.getDate();;
        String replyMessagePropertie = String.format("%s%d", "reply.prediction", info);
        return messagesService.getReplyText(replyMessagePropertie);
    }
}
