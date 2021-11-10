package org.example.TelegramProject.cashe;

import lombok.NonNull;
import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.api.handlers.UserProfileData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCashe {

    private Map<@NonNull Long, BotState> usersBotStates = new HashMap<@lombok.NonNull Long, BotState>();
    private Map<@NonNull Long, UserProfileData> usersProfileData = new HashMap<@lombok.NonNull Long, UserProfileData>();

    @Override
    public void setUsersCurrentBotState(@lombok.NonNull Long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(@lombok.NonNull Long userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.APART_SEARCH;
        }

        return botState;
    }

    @Override
    public UserProfileData getUserProfileData(@lombok.NonNull Long userId) {
        UserProfileData userProfileData = usersProfileData.get(userId);
        if (userProfileData == null) {
            userProfileData = new UserProfileData();
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(@lombok.NonNull Long userId, UserProfileData userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }

}
