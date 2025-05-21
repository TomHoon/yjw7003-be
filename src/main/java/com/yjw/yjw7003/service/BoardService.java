package com.yjw.yjw7003.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yjw.yjw7003.dto.BoardDTO;
import com.yjw.yjw7003.entity.Board;
import com.yjw.yjw7003.entity.Member;
import com.yjw.yjw7003.repository.BoardRepository;
import com.yjw.yjw7003.repository.MemberRepository;
import com.yjw.yjw7003.utils.UploadFile;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;
  private final String uploadDir = "uploads";

  public List<BoardDTO> 게시글리스트조회() {
    List<BoardDTO> list = boardRepository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
    return list;
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

    List<MultipartFile> list = param.getFiles(); // 사용자가 보낸 이미지 리스트
    List<UploadFile> uploadList = new ArrayList<>(); // Embeded Table에 들어갈 녀석들

    if (list != null && list.size() != 0) {

      for (MultipartFile m : list) {
        String originName = m.getOriginalFilename(); // 요청보낸 파일에서 파일명 뽑기
        String newFileName = UUID.randomUUID() + "_" + originName; // 새로운 파일명으로 수정한다.

        try {
          Path uploadPath = Paths.get(uploadDir);

          if (!Files.exists(uploadPath)) { // 디렉토리 없는 경우 생성

            Files.createDirectories(uploadPath);

          } else {

            Path filePath = uploadPath.resolve(newFileName); // Path 자체는 파일까지 Path로 처리
            Files.copy(m.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            UploadFile file = UploadFile.builder()
                .fileName(newFileName)
                .filePath(uploadPath.toString())
                .size(m.getSize())
                .build();

            uploadList.add(file);

          }

        } catch (Exception e) {

          System.out.println(e);

        }

      }
    }

    // Board에 넣기 위한
    // 게시글 작성 회원 엔티티 조회
    Member member = memberRepository.findById(param.getMno()).orElseThrow();

    Board board = Board.builder()
        .title(param.getTitle())
        .content(param.getContent())
        .writer(param.getWriter())
        .files(uploadList)
        .member(member)
        .build();

    Board result = boardRepository.save(board); // Board 테이블에 Insert

    param.setBno(result.getBno());
    param.setFileNames(uploadList.stream().map(UploadFile::getFilePath).collect(Collectors.toList()));
    param.setCreatedAt(result.getCreatedAt());
    param.setModifyAt(result.getModifyAt());

    return param;
  }

}
