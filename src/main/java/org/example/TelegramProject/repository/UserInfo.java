package org.example.TelegramProject.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.TelegramProject.api.BotState;
import org.springframework.lang.Nullable;

import java.util.Locale;

public class UserInfo {

    @Getter
    private final Long chatId;
    @Getter @Setter
    @Nullable
    private UserPreference userPreference;
    @Getter @Setter
    private Locale userLocale;

    public UserInfo(Long chatId, BotState state, UserPreference userPreference) {
        this.chatId = chatId;

        this.userPreference = userPreference;
    }

    public UserInfo(Long chatId) {
        this.chatId = chatId;
    }
}
