package com.yjw.yjw7003.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjw.yjw7003.dto.MemberDTO;
import com.yjw.yjw7003.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login")
  public Boolean 회원로그인(@RequestBody MemberDTO dto) {
    return memberService.회원로그인(dto);
  }

}
