package com.yjw.yjw7003.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

  public Boolean 회원가입(MemberDTO dto) {

    if(memberRepository.existsByMemberId(dto.getMemberId())) {
    throw new RuntimeException("이미 가입된 아이디입니다.");
    }
      Member member = Member.builder()
      .memberId(dto.getMemberId())
      .memberPw(dto.getMemberPw())
      .memberNickname(dto.getMemberNickname())
      .build();

  memberRepository.save(member);
  return true;
  }

  public List<MemberDTO> 전체회원조회() {
    List<Member> members = memberRepository.findAll();
    return members.stream()
      .map(m -> MemberDTO.builder()
      .mno(m.getMno())
      .memberId(m.getMemberId())
      .memberPw(m.getMemberPw())
      .memberDegree(m.getMemberDegree())
      .memberNickname(m.getMemberNickname())
      .memberIsSocial(m.getMemberIsSocial())
      .build())

      .collect(Collectors.toList());
     }
}