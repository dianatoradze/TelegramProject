package org.example.TelegramProject.controller;

import org.example.TelegramProject.Bot;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final Bot telegramBot;

    public WebHookController(Bot telegramBot) {
        this.telegramBot = telegramBot;
    }
@RequestMapping(value = "/", method = RequestMethod.POST)

//@GetMapping("/")

public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);

}
}
