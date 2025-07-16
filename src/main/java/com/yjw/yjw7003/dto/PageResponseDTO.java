package com.yjw.yjw7003.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {

  private List<T> dtoList = new ArrayList<>();
  private int totalPages = 0;
  private long totalElements = 0;
  private int startPage = 0;
  private int currentGroup = 0;
  private boolean hasNextGroup = false;
  private boolean hasPreviousGroup = false;

  public PageResponseDTO(Page<T> result) {
    this.dtoList = result.getContent();
    this.totalPages = result.getTotalPages();
    this.totalElements = result.getTotalElements();
  }
}
