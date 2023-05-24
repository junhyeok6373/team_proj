package com.example.proj.service;

import com.example.proj.dto.GymDTO;
import com.example.proj.dto.MemberDTO;
import com.example.proj.entity.MemberEntity;
import com.example.proj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 회원가입 서비스
    public MemberDTO save(MemberDTO memberDTO) {
        // 1. Dto -> Entity 로 변환
        // 2. repository 의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        memberEntity = memberRepository.save(memberEntity);

        // repository 의 save 메서드 호출(조건, Entity 객체를 넘겨줘야 함)
        return MemberDTO.toMemberDto(memberEntity);
    }

    // 사용자 로그인 서비스
    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 ID로 DB 에서 조회한다.
            2. DB 에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
         */
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if(byMemberId.isPresent()) {
            // 조회 결과가 있다. (해당 아이디를 가진 회원정보가 있다.)
            MemberEntity memberEntity = byMemberId.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                // Entity -> Dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDto(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다. (해당 아이디를 가진 회원정보가 없다.)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDto(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
    public MemberDTO findByMemberId(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }


    public MemberDTO updateForm(String myId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(myId);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    // 아이디 중복 체크
    public String idCheck(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        if (byMemberId.isPresent()) {
            // 조회 결과가 있다 -> 사용할 수 없다.
            return "fail";
        } else {
            // 조회 결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }
}
