//package org.example.TelegramProject.controller;
//
//import org.example.TelegramProject.Bot;
//import org.springframework.web.bind.annotation.*;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@RestController
//public class WebHookController {
//    private final Bot telegramBot;
//
//    public WebHookController(Bot telegramBot) {
//        this.telegramBot = telegramBot;
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//
//    public void onUpdateReceived(@RequestBody Update update) {
//         telegramBot.onUpdateReceived(update);
//    }
//}
