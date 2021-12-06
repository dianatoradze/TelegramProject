package org.example.TelegramProject.repository;

import org.example.TelegramProject.model.UserProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProfileDataRepository extends JpaRepository<UserProfileData, Integer> {
    @Query("select u from UserProfileData u where u.chatId = ?1")
    List<UserProfileData> findByChatId(long chatId);
    List<UserProfileData> findByChatIdAndSumAndAndApartType(long chatId, Integer sun, String apartType);

    void deleteByChatId(long chatId);


}