package controller;

import org.example.TelegramProject.Bot;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
class WebController {
    private final Bot telegramBot;

    public WebController(Bot telegramBot) {
        this.telegramBot = telegramBot;
    }
@RequestMapping(value = "/", method = RequestMethod.POST)

@GetMapping("/")

    public void onUpdateReceived(@RequestBody Update update) {
        telegramBot.onUpdateReceived(update);
    }
}