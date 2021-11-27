package org.example.TelegramProject.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


//Данные  пользователя
@Data
@Entity
@Table(name = "users")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Apart(collection = "userProfileData")

public class UserProfileData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatId")
    private long chatId;


    @Column(name = "sum")
    private String sum;
    @Column (name="apartType")
    private String apartType;
    @Column(name="dateBegin")
    private Date dateBegin;
    @Column(name="dateFinish")
    private Date dateFinish;


    private String ApartOneRoom;
    private String ApartTwoRoom;

    public UserProfileData(long chatId, String sum, String apartType, Date dateBegin, Date dateFinish) {
        this.chatId = chatId;
        this.sum = sum;
        this.apartType = apartType;
        this.dateBegin = dateBegin;
        this.dateFinish = dateFinish;

    }
}

