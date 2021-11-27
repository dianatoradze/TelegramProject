package org.example.TelegramProject.cashe;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.model.UserProfileData;

public interface DataCashe {
    void setUsersCurrentBotState(Long userId, BotState botState);

    BotState getUsersCurrentBotState(Long userId);

    UserProfileData getUserProfileData(Long userId);

    void saveUserProfileData(Long userId, UserProfileData userProfileData);
}
