package api.handlers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

//Данные анкеты пользователя

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
        String name;

        int sum;
    }

