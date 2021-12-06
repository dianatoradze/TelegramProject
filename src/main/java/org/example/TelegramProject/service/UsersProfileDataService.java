package org.example.TelegramProject.service;

import org.example.TelegramProject.model.UserProfileData;
import org.example.TelegramProject.repository.UserProfileDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UsersProfileDataService {

private UserProfileDataRepository profileDataRepository;

    public UsersProfileDataService() {

    }
    public UsersProfileDataService(UserProfileDataRepository profileDataRepository) {
        this.profileDataRepository = profileDataRepository;
    }

    public Iterable<UserProfileData> getAllProfiles() {
        return  profileDataRepository.findAll();
    }
    @Transactional
    public void saveUserProfileData(UserProfileData userProfileData) {

        profileDataRepository.save(userProfileData);
    }

    public void deleteUsersProfileData(Integer profileDataId) {
        profileDataRepository.deleteById(profileDataId);
    }
    public boolean hasSubscription(UserProfileData userSubscription) {
        return profileDataRepository.findByChatIdAndSumAndAndApartType(userSubscription.getChatId(),
                Integer.valueOf(userSubscription.getSum()), userSubscription.getApartType()).size() > 0;
    }

    public Optional<UserProfileData> getUsersSubscriptionById(String subscriptionID) {
        return profileDataRepository.findById(Integer.valueOf(subscriptionID));
    }
    public List<UserProfileData> getUserProfileData(long chatId) {

        return profileDataRepository.findByChatId(chatId);
    }

}
