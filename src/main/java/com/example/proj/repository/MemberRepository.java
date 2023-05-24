package com.example.proj.repository;

import com.example.proj.dto.GymDTO;
import com.example.proj.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // Id로 회원정보를 조회 (select * from member_table where member_id = ?)
    Optional<MemberEntity> findByMemberId(String memberId);
}
