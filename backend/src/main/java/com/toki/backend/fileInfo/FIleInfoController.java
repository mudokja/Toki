package com.toki.backend.fileInfo;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.fileInfo.dto.FileInfoDTO;
import com.toki.backend.fileInfo.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/file") //API명세서 작성이 되어있지 않아 임의로 작성하였습니다.
@RequiredArgsConstructor
public class FIleInfoController {

    private final FileInfoService fileInfoService;


    // 모든 파일 정보 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<List<FileInfoDTO>>> getAllFiles() {
        List<FileInfoDTO> allFiles = fileInfoService.getAllFiles();
        return ResponseEntity.ok(CommonResponseDto.<List<FileInfoDTO>>builder()
                .resultCode(200)
                .resultMessage("파일 목록 조회 성공")
                .data(allFiles)
                .build());
    }

    // 파일 ID(혹은 Idx?)로 파일 정보 조회
    @GetMapping("/{fileId}")
    public ResponseEntity<CommonResponseDto<FileInfoDTO>> getFileById(@PathVariable Long fileId) {
        return fileInfoService.getFileById(fileId)
                .map(fileInfoDTO -> ResponseEntity.ok(CommonResponseDto.<FileInfoDTO>builder()
                        .resultCode(200)
                        .resultMessage("파일 정보 조회 성공")
                        .data(fileInfoDTO)
                        .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(CommonResponseDto.<FileInfoDTO>builder()
                                .resultCode(404)
                                .resultMessage("파일을 찾을 수 없습니다.")
                                .data(null)
                                .build()));
    }

    // 파일 저장
    @PostMapping("/upload")
    public ResponseEntity<CommonResponseDto<Object>> saveFile(@RequestParam("file") MultipartFile file) {
        try {
            // 파일 저장 로직
            FileInfoDTO savedFile = fileInfoService.saveFile(file);
            System.out.println("파일이 저장되었습니다.");
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("파일 저장 성공")
                    .data(savedFile)
                    .build());
        } catch (IOException e) {
            e.printStackTrace(); //예외가 발생할 경우 스택 트레이스를 출력하는 역할
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.builder()
                            .resultCode(400)
                            .resultMessage("파일 저장에 실패하였습니다.")
                            .build());
        }
    }

    // 파일 삭제
    @DeleteMapping("/{fileId}")
    public ResponseEntity<CommonResponseDto<Void>> deleteFile(@PathVariable Long fileId) {
        fileInfoService.deleteFile(fileId);
        return ResponseEntity.ok(CommonResponseDto.<Void>builder()
                .resultCode(200)
                .resultMessage("파일 삭제 성공")
                .build());
    }
}
