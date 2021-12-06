package org.example.TelegramProject.repository;

import org.example.TelegramProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProfileDataRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.chatId = ?1")
    List<User> findByChatId(long chatId);
    List<User> findByChatIdAndSumAndApartType(long chatId, String sum, String apartType);

    void deleteByChatId(long chatId);


}