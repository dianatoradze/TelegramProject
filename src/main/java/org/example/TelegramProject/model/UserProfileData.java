package org.example.TelegramProject.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//Данные  пользователя
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor

public class UserProfileData implements Serializable {
    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "sum")
    private String sum;
    @Column (name="apartType")
    private String apartType;
    @Column(name="dateBegin")
    private String dateBegin;
    @Column(name="dateFinish")
    private String dateFinish;
    private long chatId;

}

