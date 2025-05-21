package com.yjw.yjw7003.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yjw.yjw7003.utils.UploadFile;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private String writer;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modify_at")
    @Builder.Default
    private LocalDateTime modifyAt = LocalDateTime.now();

    @Column(name = "is_del")
    @Builder.Default
    private boolean isDel = false;

    @ElementCollection
    @CollectionTable(name = "upload_images", joinColumns = @JoinColumn(name = "bno"))
    private List<UploadFile> files = new ArrayList<>();

}
