package com.example.proj.service;

import com.example.proj.dto.ReserveDTO;
import com.example.proj.entity.MemberEntity;
import com.example.proj.entity.ReserveEntity;
import com.example.proj.entity.TrainerEntity;
import com.example.proj.repository.GymRepository;
import com.example.proj.repository.MemberRepository;
import com.example.proj.repository.ReserveRepository;
import com.example.proj.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final GymRepository gymRepository;
    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;
    private final ReserveRepository reserveRepository;

    @Transactional
    public ReserveDTO save(ReserveDTO reserveDTO) {
        Optional<TrainerEntity> trainer = trainerRepository.findTrainerById(reserveDTO.getT_id());
        Optional<MemberEntity> member = memberRepository.findById(reserveDTO.getM_id());
        // TODO: 2023-05-09 str_date를 Localdatetime으로 변환 후 변수에 담아 getDate()대신 넣는 작업 필요
        // LocalDateTime tempDate;
        ReserveEntity entity = ReserveEntity.builder().member(member.get()).trainer(trainer.get()).date(reserveDTO.getDate()).build();
        entity = reserveRepository.save(entity);

        return entityToDto(entity);
    }

    public ReserveDTO entityToDto(ReserveEntity entity) {
        ReserveDTO reserveDTO = ReserveDTO.builder().id(entity.getId())
                .date(entity.getDate())
                .build();
        return reserveDTO;
    }

}
