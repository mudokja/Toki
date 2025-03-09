package com.toki.backend.fileInfo.dto;

import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FileInfoDTO {


    private Long fileIdx;
    private String uploadBy;
    private String fileOriginName;
    private String fileName;
    private String fileType;
    private LocalDateTime createAt;
    private Long fileSize;
    private Boolean isImg;
    private String uploadPath;
    private LocalDateTime deleteAt;
}
