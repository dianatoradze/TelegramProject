package org.example.TelegramProject.repository;

import org.example.TelegramProject.model.UserProfileData;
import org.springframework.stereotype.Repository;

@Repository
//CrudRepository<UserProfileData,String>
public interface UserProfileDataRepository extends UsersRepository<UserProfileData,Long> {
    UserProfileData findByChatId(long chatId);
    void deleteByChatId(long chatId);
}
