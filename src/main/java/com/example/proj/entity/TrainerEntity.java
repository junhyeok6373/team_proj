package com.example.proj.entity;

import com.example.proj.dto.TrainerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "trainer_table")
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String trainerName;

    @Column
    private String trainerInfo;

    @Column
    private String trainerPhone_Number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "gym_idx")
    private GymEntity gym;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainer")
    @Builder.Default
    private List<com.example.proj.entity.ReserveEntity> reserves = new ArrayList<>();
    public void addReserve(com.example.proj.entity.ReserveEntity reserve){
        this.reserves.add(reserve);
    }
}
