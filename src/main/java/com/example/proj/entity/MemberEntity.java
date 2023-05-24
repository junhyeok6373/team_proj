package com.example.proj.entity;

import com.example.proj.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // unique 제약 조건 추가
    private String memberId;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberEmail;

    @Column
    private String memberPhone_Number;

    @Column
    private String memberAddress;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<ReserveEntity> reserves = new ArrayList<>();
    public void addReserve(ReserveEntity reserve){
        this.reserves.add(reserve);
    }

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone_Number(memberDTO.getMemberPhone_Number());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());

        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone_Number(memberDTO.getMemberPhone_Number());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());

        return memberEntity;
    }
}
