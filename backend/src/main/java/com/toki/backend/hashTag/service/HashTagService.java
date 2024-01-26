package com.toki.backend.hashTag.service;


import com.toki.backend.hashTag.dto.HashTagDTO;
import com.toki.backend.hashTag.entity.HashTag;
import com.toki.backend.hashTag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private HashTagRepository hashTagRepository;
    //태그 찾기, 태그 추가, 태그 수정, 태그 삭제 4개정도

    // 태그 찾기
    public List<HashTagDTO> findAllHashTags() {
        List<HashTag> hashTags = hashTagRepository.findAll();
        return hashTags.stream()
                .map(tag -> convertEntityToDto(tag))
                .collect(Collectors.toList());
    }

    // 태그 추가
    public HashTagDTO addHashTag(HashTagDTO hashTagDTO) {
        HashTag newTag = new HashTag(

                hashTagDTO.getTagName(),
                hashTagDTO.getCreatedAt(),
                hashTagDTO.getScore(),
                hashTagDTO.getLastUsedAt()
        );
        HashTag savedTag = hashTagRepository.save(newTag);
        return convertEntityToDto(savedTag);
    }

}
