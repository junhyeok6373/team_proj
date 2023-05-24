package com.example.proj.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GymDTO {
    private Long id;
    private String gymName;
    private String gymWeekday;
    private String gymWeekend;
    private String gymInfo;
    private String gymLocation;
    private String gymPrice;
    private String gymEvent;
    private String gymNumber;
}
