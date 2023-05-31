package com.example.proj.service;

import com.example.proj.dto.MemberDTO;
import com.example.proj.dto.ReserveDTO;
import com.example.proj.entity.GymEntity;
import com.example.proj.entity.MemberEntity;
import com.example.proj.entity.ReserveEntity;
import com.example.proj.entity.TrainerEntity;
import com.example.proj.repository.GymRepository;
import com.example.proj.repository.MemberRepository;
import com.example.proj.repository.ReserveRepository;
import com.example.proj.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime change_Date = LocalDateTime.parse(reserveDTO.getStr_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // LocalDateTime tempDate;
        ReserveEntity entity = ReserveEntity.builder().member(member.get()).trainer(trainer.get()).date(change_Date).build();
        entity = reserveRepository.save(entity);

        return entityToDto(entity);
    }

    @Transactional
    public ReserveDTO reserveInfo(Long id) {
        Optional<ReserveEntity> optionalReserveEntity = reserveRepository.findById(id);
        if (optionalReserveEntity.isPresent()) {
            ReserveEntity reserveEntity = optionalReserveEntity.get();
            MemberEntity memberEntity = reserveEntity.getMember();
            TrainerEntity trainerEntity = reserveEntity.getTrainer();
            GymEntity gymEntity = reserveEntity.getTrainer().getGym();

            ReserveDTO reserveDTO = entityToDto(reserveEntity);
            reserveDTO.setR_m_name(memberEntity.getMemberName());
            reserveDTO.setR_m_phone(memberEntity.getMemberPhone_Number());

            reserveDTO.setR_g_name(gymEntity.getGymName());
            reserveDTO.setR_g_location(gymEntity.getGymLocation());
            reserveDTO.setR_g_tel(gymEntity.getGymNumber());

            reserveDTO.setR_t_name(trainerEntity.getTrainerName());
            reserveDTO.setR_t_phone(trainerEntity.getTrainerPhone_Number());

            return reserveDTO;
        }
        return null;
    }

    public ReserveDTO entityToDto(ReserveEntity entity) {
        ReserveDTO reserveDTO = ReserveDTO.builder().id(entity.getId())
                .date(entity.getDate())
                .build();
        return reserveDTO;
    }

}
