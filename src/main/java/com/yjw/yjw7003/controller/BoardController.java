package com.yjw.yjw7003.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yjw.yjw7003.dto.BoardDTO;
import com.yjw.yjw7003.dto.PageResponseDTO;
import com.yjw.yjw7003.service.BoardService;
import com.yjw.yjw7003.utils.ApiResponse;
import com.yjw.yjw7003.utils.FileUtil;
import com.yjw.yjw7003.utils.UploadFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    private final FileUtil fileUtil;

    private static final String UPLOAD_DIR = "uploads/";

    @PostConstruct
    public void init() {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
            } else {
            }
        } else {
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageResponseDTO<BoardDTO>>> 게시글리스트조회(@RequestParam Map<String, String> param) {
        int page = Integer.parseInt(param.get("page"));

        PageResponseDTO<BoardDTO> resDTO = boardService.게시글리스트조회(page);
        return ResponseEntity.ok(ApiResponse.success(resDTO));
    }

    @GetMapping("/detail/{bno}")
    public ResponseEntity<ApiResponse<BoardDTO>> 게시글상세조회(@PathVariable(name = "bno") Long bno) {

        BoardDTO dto = boardService.게시글상세조회(bno);

        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    @PostMapping("/regist")
    public ResponseEntity<ApiResponse<BoardDTO>> 게시글등록(@RequestPart("board") BoardDTO param,
            @RequestPart(value = "attachList", required = false) List<MultipartFile> attachList) {

        List<UploadFile> fileList = new ArrayList<>();

        // 파일 존재시 서버에 저장
        // 엔티티형태로 리스트에 다시 담기
        if (attachList != null && attachList.size() > 0) {
            for (MultipartFile f : attachList) {
                String 저장파일명 = fileUtil.saveFile(f);
                String filePath = 저장파일명;
                long size = f.getSize();

                UploadFile file = UploadFile.builder()
                        .fileName(저장파일명)
                        .filePath(filePath)
                        .size(size)
                        .build();

                fileList.add(file);

            }
        }

        // 엔티티 형태 담은 리스트 넘기기 위해 파람에 넣어줌
        param.setFileUploadList(fileList);

        BoardDTO dto = boardService.게시글등록(param);

        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadImage(@RequestBody MultipartFile file) {
        String savedPath = fileUtil.saveFile(file);

        return ResponseEntity.ok(ApiResponse.success(Map.of("imageURL", savedPath)));
    }

}