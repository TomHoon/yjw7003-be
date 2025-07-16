package com.yjw.yjw7003.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yjw.yjw7003.dto.BoardDTO;
import com.yjw.yjw7003.dto.PageRequestDTO;
import com.yjw.yjw7003.dto.PageResponseDTO;
import com.yjw.yjw7003.entity.Board;
import com.yjw.yjw7003.entity.Member;
import com.yjw.yjw7003.repository.BoardRepository;
import com.yjw.yjw7003.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;

  public PageResponseDTO<BoardDTO> 게시글리스트조회(int page) {

    // NEW VERSION >>>>
    int size = 10;

    PageRequestDTO dto = PageRequestDTO.builder()
        .page(page)
        .size(size)
        .build();

    Pageable pageable = dto.getPageable(Sort.by("bno"));
    Page<BoardDTO> res = boardRepository.findAll(pageable).map(BoardDTO::new);

    PageResponseDTO<BoardDTO> resDTO = new PageResponseDTO<>(res);

    int currentGroup = page / size;
    int startPage = currentGroup * size + 1;
    int endPage = Math.min(startPage + size - 1, res.getTotalPages());

    boolean hasNextGroup = endPage < res.getTotalPages();
    boolean hasPrevGroup = startPage > 1;

    resDTO.setHasNextGroup(hasNextGroup);
    resDTO.setHasPreviousGroup(hasPrevGroup);
    resDTO.setStartPage(startPage);
    resDTO.setCurrentGroup(currentGroup);

    return resDTO;

    // OLD VERSION >>>>
    // List<BoardDTO> list =
    // boardRepository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
    // return list;
  }

  public BoardDTO 게시글상세조회(Long bno) {

    Board board = boardRepository.findById(bno)
        .orElseThrow(() -> new EntityNotFoundException("Board Not Found"));

    BoardDTO dto = BoardDTO.builder()
        .bno(board.getBno())
        .title(board.getTitle())
        .content(board.getContent())
        .writer(board.getWriter())
        .isDel(board.isDel())
        .createdAt(board.getCreatedAt())
        .fileUploadList(board.getFiles())
        .modifyAt(board.getModifyAt())
        .build();

    return dto;
  }

  /**
   * 게시글 등록
   * 
   * @param
   * @return
   */
  public BoardDTO 게시글등록(BoardDTO param) {

    // New Version >>>>

    // Board 엔티티의 Member를 가져옴
    String memberId = param.getWriter();
    Member m = memberRepository.findByMemberId(memberId).orElseThrow();

    // 멤버, ElementCollection 모두 결합하여 저장
    Board b = Board.builder()
        .title(param.getTitle())
        .content(param.getContent())
        .files(param.getFileUploadList())
        .member(m)
        .build();

    Board resultBoard = boardRepository.save(b);

    return new BoardDTO(resultBoard);
  }

}
