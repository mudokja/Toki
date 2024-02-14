package com.toki.backend.hashTag.service;

import com.toki.backend.hashTag.dto.HashTagStatDTO;
import com.toki.backend.hashTag.entity.HashTagStat;
import com.toki.backend.hashTag.repository.HashTagStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HashTagStatService {

    private final HashTagStatRepository hashTagStatRepository;


    //가장 많이 사용된 태그를 조회하는 메서드 (인기있는 상위 태그 5개만..)
//    public List<HashTagStatDTO> getMostUsedTags() {
//        List<HashTagStat> mostUsedTags = hashTagStatRepository.findTop5();
//        return mostUsedTags.stream()
//                .map(this::convertEntityToDTO)
//                .collect(Collectors.toList());
//    }



//      해시태그 스탯 엔티티를 DTO로 변환하는 메서드.

    private HashTagStatDTO convertEntityToDTO(HashTagStat hashTagStat) {
        return new HashTagStatDTO(
                hashTagStat.getIdx(),
                hashTagStat.getTagName(),
                hashTagStat.getCreateAt(),
                hashTagStat.getScore()
        );
    }
}
