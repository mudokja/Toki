package com.toki.backend.hashTag.service;


import com.toki.backend.hashTag.dto.HashTagStatDTO;
import com.toki.backend.hashTag.entity.HashTagStat;
import com.toki.backend.hashTag.repository.HashTagRepository;
import com.toki.backend.hashTag.repository.HashTagStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;


    private final HashTagStatRepository hashTagStatRepository;


    // 태그 이름이 없으면 새로 생성하고, 있으면 HashTagStat의 score 값을 증가.
    public HashTagStatDTO createOrUpdateHashTag(String tagName) {
        // 태그 이름으로 HashTagStat 조회
        Optional<HashTagStat> optionalHashTagStat = hashTagStatRepository.findByTagName(tagName);
        HashTagStatDTO result;


        if (optionalHashTagStat.isPresent()) {
            // 이미 존재하는 경우: score 증가
            HashTagStat hashTagStat = optionalHashTagStat.get();
            hashTagStat.setScore(hashTagStat.getScore() + 1);
            hashTagStatRepository.save(hashTagStat);
            result = convertEntityToDTO(hashTagStat);
        } else {
            // 존재하지 않는 경우: 새로 생성
            HashTagStat newHashTagStat = HashTagStat.builder()
                    .tagName(tagName)
                    .createAt(LocalDateTime.now())
                    .score(1) // 새로 생성되었으므로 1로 초기화
                    .build();
            HashTagStat savedHashTagStat = hashTagStatRepository.save(newHashTagStat);
            result = convertEntityToDTO(savedHashTagStat);
        }

        return result;
    }

    // HashTagStat 엔티티를 DTO로 변환하는 메서드
    private HashTagStatDTO convertEntityToDTO(HashTagStat hashTagStat) {
        return new HashTagStatDTO(
                hashTagStat.getIdx(),
                hashTagStat.getTagName(),
                hashTagStat.getCreateAt(),
                hashTagStat.getScore()
        );
    }
}
