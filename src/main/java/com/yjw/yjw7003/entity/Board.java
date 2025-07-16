package com.yjw.yjw7003.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yjw.yjw7003.utils.UploadFile;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class })
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  @Column(name = "created_at")
  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "modify_at")
  @Builder.Default
  private LocalDateTime modifyAt = LocalDateTime.now();

  private String title;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String content;

  private String writer;

  @Column(name = "is_del")
  @Builder.Default
  private boolean isDel = false;

  @ElementCollection
  @CollectionTable(name = "upload_image", joinColumns = @JoinColumn(name = "bno"))
  @Builder.Default
  List<UploadFile> files = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

}
