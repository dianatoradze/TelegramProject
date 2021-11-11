package org.example.TelegramProject.api.handlers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

//Данные  пользователя

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
    String apartOneRoom;
    String apartTwoRoom;
    int sumBegin;
    int sumFinish;
    Date dateBegin;
    Date dateFinishReceived;
    }

