package com.yjw.yjw7003.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjw.yjw7003.dto.MemberDTO;
import com.yjw.yjw7003.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login")
  public Boolean 회원로그인(@RequestBody MemberDTO dto, HttpServletResponse response) {
    Boolean isLogged = memberService.회원로그인(dto);

    if (!isLogged)
      return false;

    else {
      int maxAge = 60 * 60;

      ResponseCookie cookie = ResponseCookie
          .from("token-test", "1234444")
          .domain("localhost")
          .maxAge(maxAge)
          .sameSite("None")
          .secure(true)
          .httpOnly(true)
          .path("/")
          .build();

      response.addHeader("Set-Cookie", cookie.toString());

      return isLogged;

    }

  }

}
