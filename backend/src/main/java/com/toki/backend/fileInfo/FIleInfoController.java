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
@RequestMapping("/files") //아직 API명세서가 없음...
@RequiredArgsConstructor
public class FIleInfoController {

    private final FileInfoService fileInfoService;


    // 모든 파일 정보 조회
    @GetMapping
    public ResponseEntity<List<FileInfoDTO>> getAllFiles() {
        List<FileInfoDTO> allFiles = fileInfoService.getAllFiles();
        return ResponseEntity.ok(allFiles);
    }

    // 파일 ID(혹은 Idx?)로 파일 정보 조회
    @GetMapping("/{fileId}")
    public ResponseEntity<FileInfoDTO> getFileById(@PathVariable Long fileId) {
        return fileInfoService.getFileById(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
                    .referenceUri(null)
                    .build());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.builder()
                            .resultCode(400)
                            .resultMessage("파일 저장에 실패하였습니다.")
                            .build());
        }
    }

    // 파일 삭제
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) {
        fileInfoService.deleteFile(fileId);
        System.out.println("파일이 삭제되었습니다.");
        return ResponseEntity.noContent().build();
    }
}
