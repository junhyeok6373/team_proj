package com.example.proj.repository;

import com.example.proj.entity.GymEntity;
import com.example.proj.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {
    @Query(value = "select g from ReserveEntity g where g.id = :id")
    Optional<ReserveEntity> findReserveById(@Param("id") Long id);
}
