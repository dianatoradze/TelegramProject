package org.example.TelegramProject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProfileDataRepository extends JpaRepository<UserProfileData, Integer> {
    //@Query("select u from UserProfileData u where u.chatId = ?1")
    List<UserProfileData> findByChatId(long chatId);
    List<UserProfileData> findByChatIdAndSumAndAndApartType(long chatId, Integer sun, String dateBegin);


//    @Override
//    List<UserProfileData> findAllById(Iterable<String> strings);

    void deleteByChatId(long chatId);


}