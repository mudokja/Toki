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


    // 태그 찾기
    public List<HashTagDTO> findAllHashTags() {
        List<HashTag> hashTags = hashTagRepository.findAll();
        return hashTags.stream()
                .map(tag -> convertEntityToDto(tag))
                .collect(Collectors.toList());
    }
}
