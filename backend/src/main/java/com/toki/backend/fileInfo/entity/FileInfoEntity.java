package com.toki.backend.fileInfo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;




@Entity
@Getter
@Setter
@ToString
@Table(name = "file_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EntityListeners(FileInfoEntityListener.class)  // 엔티티 리스너 등록
public class FileInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long fileIdx;

    @Column(length = 30)
    private String uploadBy;

    @Column(length = 100)
    private String fileOriginName;

    @Column(length = 100)
    private String fileName;

    @Column(length = 10)
    private String fileType;

    @Column
    private LocalDateTime createAt;

    @Column
    private Long fileSize;

    @Column(name = "is_img")
    private Boolean isImg;

    @Column(name = "upload_path", length = 255)
    private String uploadPath;

    @Column(name = "delete_at")
    private LocalDateTime deleteAt;



}
