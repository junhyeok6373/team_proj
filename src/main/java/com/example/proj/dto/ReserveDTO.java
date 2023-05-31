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

    /*
    예약자 정보
  예약자 성함 :
 예약자 전화번호 :

예약한 헬스장 정보
  헬스장 이름 :
  헬스장 위치 :
  헬스장 전화번호 :

예약한 트레이너 정보
  트레이너 이름 :
  트레이너 전화번호 :

    */

    private String r_m_name;
    private String r_m_phone;

    private String r_g_name;
    private String r_g_location;
    private String r_g_tel;

    private String r_t_name;
    private String r_t_phone;

}
