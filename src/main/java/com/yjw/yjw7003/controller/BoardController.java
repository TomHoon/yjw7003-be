package com.yjw.yjw7003.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjw.yjw7003.dto.BoardDTO;
import com.yjw.yjw7003.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/detail/{bno}")
    public BoardDTO 게시글상세조회(@PathVariable(name = "bno") Long bno) {

        BoardDTO dto = boardService.게시글상세조회(bno);

        return dto;
    }

    @PostMapping("/regist")
    public BoardDTO 게시글등록(@ModelAttribute BoardDTO param) {
        return boardService.게시글등록(param);
    }

}