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

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String sum;

    private String apartType;
    private String dateBegin;

    private String dateFinish;

    private long chatId;

    private List<ApartEntity> apartEntityList = new ArrayList<>();

    @Override
    public String toString() {
        return
               "Ваша планка стоимости аренды - " + sum

              + ", " +  " Дата начала аренды - " + dateBegin + ", " +
                "Дата окончания - " + dateFinish;
    }
}

