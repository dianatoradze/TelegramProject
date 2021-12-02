package org.example.TelegramProject;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.TelegramProject.api.TelegramFacade;
import org.example.TelegramProject.parcer.Apart;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.net.URL;

@Getter
@Setter
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private String botToken = "2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    private String botUserName = "@testerforhelp_bot";
    private String webHookPath = "https://21b0-2a00-1fa2-279-4176-b153-358a-e9ab-8a37.ngrok.io";
    private final TelegramFacade telegramFacade;
    Apart apart = new Apart();
    private long chatId;

    public Bot(TelegramFacade telegramFacade) {

        this.telegramFacade = telegramFacade;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        final BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);
        if (update.hasMessage()) {
            log.info("TelegramBot onUpdateReceived {}", update);

            sendMessage(replyMessageToUser);

        }
        // обработка отклика с клавиатуры
        if (update.hasCallbackQuery()) {
            try {
                execute(replyMessageToUser);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message " + e.getMessage());
        }
    }


    @SneakyThrows
    public String getInfo(String message)  {
//
//        URL url = new URL(apart.getImage());
//        BufferedImage img = ImageIO.read(url);
//        // качаем изображение в буфер
//        InputFile outputfile = new InputFile("image.jpg");
//        //создаем новый файл в который поместим  изображение
//        ImageIO.write(img, "jpg", (ImageOutputStream) outputfile);
//
//        //преобразовуем  буферное изображение в новый файл
//
//        sendPhoto(chatId, outputfile);

        String info = apart.getTitle()
                + "\nАдрес" + apart.getAdress()
                + "\nКоличество комнат" + apart.getApartType()
                + "\n\nОписание\n" + apart.getDescription()
                + "\n\nОписание\n" + apart.getSum()
                + "\n\nВремя размещения объявления " + apart.getDate();

        return info;
    }

    @SneakyThrows
    public void sendPhoto(long chatId, InputFile imageCaption) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setCaption(String.valueOf(imageCaption));
        execute(sendPhoto);
    }

}


