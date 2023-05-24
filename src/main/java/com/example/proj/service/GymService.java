package com.example.proj.service;

import com.example.proj.dto.GymDTO;
import com.example.proj.entity.GymEntity;
import com.example.proj.repository.GymRepository;
import com.example.proj.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;
    private final TrainerRepository trainerRepository;



    public GymDTO findGymById(Long id) {
        GymDTO gymDTO = null;
        Optional<GymEntity> optionalGymEntity = gymRepository.findGymById(id);
        if (optionalGymEntity.isPresent()) {
            GymEntity gymEntity = optionalGymEntity.get();
            gymDTO = entityToDto(gymEntity);
        }
        return gymDTO;
    }

    public GymDTO findGymByTrainerId(Long id) {
        GymDTO gymDTO = null;
        Optional<GymEntity> optionalGymEntity = trainerRepository.findGymByTrainerId(id);
        if (optionalGymEntity.isPresent()) {
            GymEntity gymEntity = optionalGymEntity.get();
            gymDTO = entityToDto(gymEntity);
        }
        return gymDTO;
    }

    public List<GymDTO> finAllGym() {
        List<GymDTO> gymDTOS = null;
        Optional<List<GymEntity>> optionalGymEntities = gymRepository.findAllGym();
        if (optionalGymEntities.isPresent()) {
            List<GymEntity> gymEntity = optionalGymEntities.get();
            gymDTOS = toList(gymEntity);
        }
        return gymDTOS;
    }

    public GymDTO entityToDto(GymEntity entity) {
        GymDTO gymDTO = GymDTO.builder().id(entity.getId())
                .gymName(entity.getGymName())
                .gymNumber(entity.getGymNumber())
                .gymWeekday(entity.getGymWeekday())
                .gymWeekend(entity.getGymWeekend())
                .gymInfo(entity.getGymInfo())
                .gymLocation(entity.getGymLocation())
                .gymPrice(entity.getGymPrice())
                .gymEvent(entity.getGymEvent())
                .build();
        return gymDTO;
    }

    public List<GymDTO> toList(List<GymEntity> entities) {
        return entities.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }


}
