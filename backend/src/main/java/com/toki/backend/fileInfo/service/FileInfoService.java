package com.toki.backend.fileInfo.service;

import com.toki.backend.fileInfo.dto.FileInfoDTO;
import com.toki.backend.fileInfo.entity.FileInfoEntity;
import com.toki.backend.fileInfo.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;
g
    // 모든 파일 정보 조회
    public List<FileInfoDTO> getAllFiles() {
        List<FileInfoEntity> fileInfoEntities = fileInfoRepository.findAll();
        return fileInfoEntities.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    // 파일 ID로 파일 정보 조회
    public Optional<FileInfoDTO> getFileById(Long fileId) {
        System.out.println("정보조회");
        return fileInfoRepository.findById(fileId)
                .map(this::convertEntityToDTO); 
    }

    // 파일 저장
    public FileInfoDTO saveFile(MultipartFile file) throws IOException {
        FileInfoDTO fileInfoDTO = convertMultipartFileToDTO(file);
        FileInfoEntity fileInfoEntity = convertDTOToEntity(fileInfoDTO);
        FileInfoEntity savedFileInfo = fileInfoRepository.save(fileInfoEntity);
        return convertEntityToDTO(savedFileInfo);
    }


    // 파일 삭제
    public void deleteFile(Long fileId) {
        fileInfoRepository.deleteById(fileId);
    }

    // 엔티티를 DTO로 변환 (GPT)
    private FileInfoDTO convertEntityToDTO(FileInfoEntity fileInfoEntity) {
        return new FileInfoDTO(
                fileInfoEntity.getFileIdx(),
                fileInfoEntity.getUploadBy(),
                fileInfoEntity.getFileOriginName(),
                fileInfoEntity.getFileName(),
                fileInfoEntity.getFileType(),
                fileInfoEntity.getCreateAt(),
                fileInfoEntity.getFileSize(),
                fileInfoEntity.getIsImg(),
                fileInfoEntity.getUploadPath(),
                fileInfoEntity.getDeleteAt()
        );
    }


    // DTO를 엔티티로 변환 (GPT)
    private FileInfoEntity convertDTOToEntity(FileInfoDTO fileInfoDTO) {
        return FileInfoEntity.builder()
                .fileIdx(fileInfoDTO.getFileIdx())
                .uploadBy(fileInfoDTO.getUploadBy())
                .fileOriginName(fileInfoDTO.getFileOriginName())
                .fileName(fileInfoDTO.getFileName())
                .fileType(fileInfoDTO.getFileType())
                .createAt(fileInfoDTO.getCreateAt())
                .fileSize(fileInfoDTO.getFileSize())
                .isImg(fileInfoDTO.getIsImg())
                .uploadPath(fileInfoDTO.getUploadPath())
                .deleteAt(fileInfoDTO.getDeleteAt())
                .build();
    }
}
