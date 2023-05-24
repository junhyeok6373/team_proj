package com.example.proj.repository;

import com.example.proj.entity.GymEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<GymEntity, Long> {

    @Query(value = "select g from GymEntity g where g.id = :id")
    Optional<GymEntity> findGymById(@Param("id") Long id);

    @Query(value = "select g from GymEntity g")
    Optional<List<GymEntity>> findAllGym();
}
