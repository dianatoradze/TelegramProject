package org.example.TelegramProject.repository;

import org.example.TelegramProject.api.BotState;
import org.example.TelegramProject.model.UserProfileData;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

    public interface UsersRepository<UserProfileData, Long> extends JpaRepository <UserProfileData,Long>{

//        Optional<UserProfileData> getUserPreference(T userChatId);
//
//        void setUserPreference(T userChatId, @Nullable UserProfileData userPreference);
//
//
//        List<UserInfo> getSubscribedUserInfos();
//
//        Locale getUserLocale(T userChatId);
//
//        void setUserLocale(T userChatId, Locale userLocale);
    }

