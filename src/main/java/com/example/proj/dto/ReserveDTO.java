package com.example.proj.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReserveDTO {
    // 예약 테이블 정보
    private Long id;
    private LocalDateTime date;

    // str_date는 프론트로부터 받아오는 정보
    private Long m_id;
    private Long t_id;
    private String str_date;
}
