package com.yjw.yjw7003.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yjw.yjw7003.dto.MemberDTO;
import com.yjw.yjw7003.service.MemberService;

@SpringBootTest
public class MemberServiceTests {

  @Autowired
  private MemberService memberService;

  @Test
  public void insert() {
    MemberDTO dto = MemberDTO.builder()
        .memberId("test1")
        .memberPw("1234")
        .memberDegree("USER")
        .memberNickname("admin")
        .build();
    memberService.회원가입(dto);
  }
}
