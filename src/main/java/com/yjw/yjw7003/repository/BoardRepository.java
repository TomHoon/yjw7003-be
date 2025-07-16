package com.yjw.yjw7003.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yjw.yjw7003.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  Page<Board> findAll(Pageable pageable);
}
