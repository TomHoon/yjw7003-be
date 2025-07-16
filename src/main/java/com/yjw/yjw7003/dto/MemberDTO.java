package com.yjw.yjw7003.dto;

import com.yjw.yjw7003.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

  public Long mno;
  public String memberId;
  public String memberPw;
  public String memberDegree;
  public String memberNickname;
  public String memberIsSocial;

  public MemberDTO(Member e) {
    this.memberId = e.getMemberId();
    this.memberDegree = e.getMemberDegree();
    this.memberNickname = e.getMemberNickname();
    this.memberIsSocial = e.getMemberIsSocial();
  }

}
