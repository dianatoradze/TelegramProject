package org.example.TelegramProject.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

//Данные  пользователя

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Apart(collection = "userProfileData")
public class UserProfileData {
    @Id

    String apartType;
    String apartOneRoom;
    String apartTwoRoom;
    String sum;
    Date dateBegin;
    Date dateFinish;

    @Override
    public String toString() {
        return "UserProfileData{" +
                "apartType='" + apartType + '\'' +
                ", sum='" + sum + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateFinish=" + dateFinish +
                '}';
    }
}

