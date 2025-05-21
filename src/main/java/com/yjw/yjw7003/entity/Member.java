package com.yjw.yjw7003.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  @Column(name = "member_id")
  private String memberId;

  @Column(name = "member_pw")
  private String memberPw;

  @Column(name = "member_degree")
  private String memberDegree;

  @Column(name = "member_nickname")
  private String memberNickname;

  @Column(name = "member_is_social")
  private String memberIsSocial;

}
