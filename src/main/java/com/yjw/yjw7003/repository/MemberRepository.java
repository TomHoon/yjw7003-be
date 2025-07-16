package com.yjw.yjw7003.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjw.yjw7003.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByMemberId(String memberId);

  boolean existsByMemberId(String memberId);
}