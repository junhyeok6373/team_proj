package com.example.proj.service;

import com.example.proj.dto.MemberDTO;
import com.example.proj.dto.TrainerDTO;
import com.example.proj.entity.MemberEntity;
import com.example.proj.entity.TrainerEntity;
import com.example.proj.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerDTO findTrainerById(Long id) {
        TrainerDTO trainerDTO = null;
        Optional<TrainerEntity> optionalTrainerEntity = trainerRepository.findTrainerById(id);
        if(optionalTrainerEntity.isPresent()) {
            TrainerEntity trainerEntity = optionalTrainerEntity.get();
            trainerDTO = entityToDto(trainerEntity);
        }
        return trainerDTO;
    }

    public List<TrainerDTO> findAllTrainer(Long id) {
        List<TrainerDTO> trainerDTOS = null;
        Optional<List<TrainerEntity>> optionalTrainerEntities = trainerRepository.findAllTrainer(id);
        if(optionalTrainerEntities.isPresent()) {
            List<TrainerEntity> trainerEntity = optionalTrainerEntities.get();
            trainerDTOS = toList(trainerEntity);
        }
        return trainerDTOS;
    }

    public TrainerDTO entityToDto(TrainerEntity entity) {
        TrainerDTO trainerDTO = TrainerDTO.builder().id(entity.getId())
                .trainerName(entity.getTrainerName())
                .trainerInfo(entity.getTrainerInfo())
                .trainerPhone_Number(entity.getTrainerPhone_Number())
                .build();
        return trainerDTO;
    }

    public List<TrainerDTO> toList(List<TrainerEntity> entities) {
        return entities.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }
}
