package org.example.TelegramProject.cashe;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCashe {

    private Map<Long, BotState> usersBotStates = new HashMap<@lombok.NonNull Long, BotState>();
    private Map<Long, User> usersProfileData = new HashMap<Long, User>();

    @Override
    public void setUsersCurrentBotState(Long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(Long userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.APART_SEARCH;
        }

        return botState;
    }

    @Override
    public User getUserProfileData(Long userId) {
        User userProfileData = usersProfileData.get(userId);
        if (userProfileData == null) {
            userProfileData = new User();//изменить
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(Long userId, User userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }

}
