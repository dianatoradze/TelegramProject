package org.example.TelegramProject.cashe;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.handlers.UserProfileData;

public interface DataCashe {
    void setUsersCurrentBotState(@lombok.NonNull Long userId, BotState botState);

    BotState getUsersCurrentBotState(@lombok.NonNull Long userId);

    UserProfileData getUserProfileData(@lombok.NonNull Long userId);

    void saveUserProfileData(@lombok.NonNull Long userId, UserProfileData userProfileData);
}
