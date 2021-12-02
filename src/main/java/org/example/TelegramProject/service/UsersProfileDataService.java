package org.example.TelegramProject.service;

import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.model.UserProfileDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersProfileDataService {

private UserProfileDataRepository profileDataRepository;

    public UsersProfileDataService() {
    }

    public List<UserProfileData> getAllProfiles() {
        return  profileDataRepository.findAll();
    }

    public void saveUserProfileData(UserProfileData userProfileData) {
        profileDataRepository.save(userProfileData);
    }

    public void deleteUsersProfileData(String profileDataId) {
        profileDataRepository.deleteById(profileDataId);
    }

    public UserProfileData getUserProfileData(long chatId) {

        return (UserProfileData) profileDataRepository.findByChatId(chatId);
    }

}
