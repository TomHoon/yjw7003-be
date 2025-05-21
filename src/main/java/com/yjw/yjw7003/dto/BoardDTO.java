package com.yjw.yjw7003.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yjw.yjw7003.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  public Long bno;
  public Long mno;
  public String title;
  public String content;
  public String writer;
  public Boolean isDel;
  public LocalDateTime createdAt;
  public LocalDateTime modifyAt;

  @Builder.Default
  public List<String> fileNames = new ArrayList<>();

  @Builder.Default
  @JsonIgnore
  public List<MultipartFile> files = new ArrayList<>();

  public BoardDTO(Board board) {
    this.bno = board.getBno();
    this.title = board.getTitle();
    this.content = board.getContent();
    this.writer = board.getWriter();
    this.isDel = board.isDel();
    this.createdAt = board.getCreatedAt();
    this.modifyAt = board.getModifyAt();
  }
}
