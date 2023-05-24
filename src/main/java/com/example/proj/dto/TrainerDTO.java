package com.example.proj.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TrainerDTO {
    private Long id;
    private String trainerName;
    private String trainerInfo;
    private String trainerPhone_Number;
}
