package org.example.TelegramProject.service;

import lombok.SneakyThrows;
import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.repository.UserProfileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersProfileDataService {

private UserProfileDataRepository profileDataRepository;

//    public UsersProfileDataService(UserProfileDataRepository profileDataRepository) {
//        this.profileDataRepository = profileDataRepository;
//    }
    public List<UserProfileData> getAllProfiles() {
        return  profileDataRepository.findAll();
    }

//    public void saveUserProfileData(UserProfileData userProfileData) {
//        profileDataRepository.save(userProfileData);
//    }

    public void deleteUsersProfileData(String profileDataId) {
        profileDataRepository.deleteById(Long.valueOf(profileDataId));
    }

    public UserProfileData getUserProfileData(long chatId) {
        UserProfileData user = null;
        Optional<UserProfileData> optional = profileDataRepository.findById(chatId);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

}
