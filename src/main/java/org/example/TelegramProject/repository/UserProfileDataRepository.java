package org.example.TelegramProject.repository;

import org.example.TelegramProject.model.UserProfileData;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//CrudRepository<UserProfileData,String>
//public interface UserProfileDataRepository extends JpaRepository<UserProfileData,String> {
//    @Query("select u from UserProfileData u where u.chatId = ?1")
//    UserProfileData findByChatId(long chatId);
//
//    @Override
//    List<UserProfileData> findAllById(Iterable<String> strings);
//
//    void deleteByChatId(long chatId);
//}
