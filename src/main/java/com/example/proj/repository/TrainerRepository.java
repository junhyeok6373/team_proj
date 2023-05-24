package com.example.proj.repository;

import com.example.proj.entity.GymEntity;
import com.example.proj.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    @Query(value = "select t.gym from TrainerEntity t where t.id = :id")
    Optional<GymEntity> findGymByTrainerId(@Param("id") Long id);

    @Query(value = "select t from TrainerEntity t where t.id = :id")
    Optional<TrainerEntity> findTrainerById(@Param("id") Long id);

    @Query(value = "select t from TrainerEntity t where t.gym.id = :id")
    Optional<List<TrainerEntity>> findAllTrainer(@Param("id") Long id);
}
