package com.yjw.yjw7003.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yjw.yjw7003.dto.MemberDTO;
import com.yjw.yjw7003.entity.Member;
import com.yjw.yjw7003.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Boolean 회원로그인(MemberDTO dto) {
    Optional<Member> result = memberRepository.findByMemberId(dto.getMemberId());
    Member member = result.orElseThrow();

    String 입력한비번 = dto.getMemberPw();
    String 디비비번 = member.getMemberPw();

    if (입력한비번.equals(디비비번)) {
      return true;
    } else {
      return false;
    }
  }
}