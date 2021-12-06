package org.example.TelegramProject.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Данные  пользователя
@Getter
@Setter
@Entity
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor

public class UserProfileData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@Column(name = "sum")
    private String sum;
    //@Column (name="apartType")
    private String apartType;
    //@Column(name="dateBegin")
    private String dateBegin;

    //@Column(name="dateFinish")
    private String dateFinish;

   // @Column(name="chatId")
    private long chatId;
   // @OneToMany(cascade = CascadeType.ALL)
    private List<ApartEntity> apartEntityList = new ArrayList<>();

    @Override
    public String toString() {
        return
               "Ваща планка стоимости аренды - " + sum + ", " +
                "тип квартиры - " + getApartType();
//              + ", " +  "Дата начала аренды - " + dateBegin + ", " +
//                "Дата окончания - " + dateFinish;
    }
}

