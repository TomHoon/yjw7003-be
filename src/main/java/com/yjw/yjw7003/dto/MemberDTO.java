package com.yjw.yjw7003.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class MemberDTO {

  public Long mno;
  public String memberId;
  public String memberPw;
  public String memberDegree;
  public String memberNickname;
  public String memberIsSocial;

}
