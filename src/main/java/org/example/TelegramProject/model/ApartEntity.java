package org.example.TelegramProject.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "apart")


public class ApartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private String id;

    private int sum;
    private long chatId;

    private String apartType;


    @Column(name = "adress")
    private String adress;


    @Column(name = "description")
    private String description;


}