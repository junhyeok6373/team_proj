package com.example.proj.controller;

import com.example.proj.dto.GymDTO;
import com.example.proj.dto.MemberDTO;
import com.example.proj.dto.ReserveDTO;
import com.example.proj.dto.TrainerDTO;
import com.example.proj.entity.MemberEntity;
import com.example.proj.service.GymService;
import com.example.proj.service.MemberService;
import com.example.proj.service.ReserveService;
import com.example.proj.service.TrainerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class MemberController {

    private final GymService gymService;
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final ReserveService reserveService;


    @PostMapping(value = "/default")
    public String test() {
        return "hello world";
    }

   // @PostMapping(value = "/member2")
   // public String postMemberDTO(@RequestBody MemberDTO memberDTO) {
   //     return memberDTO.toString();
    //}

    @PostMapping(value = "/member3")
    public ResponseEntity<MemberDTO> putMemberDto3(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    // 헬스장 키워드 검색 API
    @PostMapping("/search")
    public ResponseEntity<List<GymDTO>> getGymList(@RequestBody GymDTO gymDTO) {
        List<GymDTO> gymDTOList = gymService.finAllGym();
        return ResponseEntity.status(HttpStatus.OK).body(gymDTOList);
    }

    @PostMapping("/trainer/{id}")
    public ResponseEntity<List<TrainerDTO>> getTrainerList(@PathVariable Long id) {
        List<TrainerDTO> trainerDTOList = trainerService.findAllTrainer(id);
        return ResponseEntity.status(HttpStatus.OK).body(trainerDTOList);
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDTO> postRegister(@RequestBody MemberDTO memberDTO) {
        MemberDTO memberDTO1 = memberService.save(memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO1);
    }

    @GetMapping("/idCheck/{id}")
    public String idCheck(@PathVariable String id) {
        String id_result = memberService.idCheck(id);
        return id_result;
    }

    // 멤버, 트레이너 아이디, 예약시간정보 API
    @PostMapping("/test1")
    public ResponseEntity<ReserveDTO> test(@RequestBody ReserveDTO reserveDTO) {
        ReserveDTO reserveDTO1 = reserveService.save(reserveDTO);
        return ResponseEntity.status(HttpStatus.OK).body(reserveDTO1);
    }

    // 회원가입, 로그인(사용자가 입력) - POST


}
