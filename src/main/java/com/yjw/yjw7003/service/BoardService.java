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
import com.yjw.yjw7003.repository.BoardRepository;
import com.yjw.yjw7003.utils.UploadFile;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final String uploadDir = "uploads";

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

  public BoardDTO 게시글등록(BoardDTO param) {

    List<MultipartFile> list = param.getFiles();
    List<UploadFile> uploadList = new ArrayList<>();

    if (list != null && list.size() != 0) {

      for (MultipartFile m : list) {
        String originName = m.getOriginalFilename();
        String newFileName = UUID.randomUUID() + "_" + originName;

        try {
          Path uploadPath = Paths.get(uploadDir);

          if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

          } else {

            Path filePath = uploadPath.resolve(newFileName);
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

    Board board = Board.builder()
        .title(param.getTitle())
        .content(param.getContent())
        .writer(param.getWriter())
        .files(uploadList)
        .build();

    Board result = boardRepository.save(board);

    param.setBno(result.getBno());
    param.setFileNames(uploadList.stream().map(UploadFile::getFilePath).collect(Collectors.toList()));
    param.setCreatedAt(result.getCreatedAt());
    param.setModifyAt(result.getModifyAt());

    return param;
  }

}
