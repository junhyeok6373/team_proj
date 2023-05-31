package com.example.proj.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gym_table")
public class GymEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String gymName;

    @Column
    private String gymNumber;

    @Column
    private String gymWeekday;

    @Column
    private String gymWeekend;

    @Column
    private String gymInfo;

    @Column
    private String gymLocation;

    @Column
    private String gymEvent;

    @Column
    private String gymPrice;

    @Column
    private String latitude;

    @Column
    private String longitude;


    @ToString.Exclude
    @OneToMany(mappedBy = "gym")
    @Builder.Default
    private List<TrainerEntity> trainers = new ArrayList<>();
    public void addTrainer(TrainerEntity trainer){
        this.trainers.add(trainer);
    }
}
