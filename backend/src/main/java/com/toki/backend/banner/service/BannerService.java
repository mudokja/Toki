package com.toki.backend.banner.service;

import com.toki.backend.banner.dto.BannerDTO;
import com.toki.backend.banner.entity.Banner;
import com.toki.backend.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;



    // 1. 모두 조회
    public List<BannerDTO> getAllBanners() {
        List<Banner> banners = bannerRepository.findAll();
        return banners.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // 2. 특정 인덱스만 조회
    public List<?> findByIdx(int idx) {
        Banner banner = bannerRepository.findByIdx(idx).orElse(null);
        return banner != null ? Collections.singletonList(banner.getName()) : Collections.emptyList();
    }


    // 3. 삭제
    public void deleteBanner(int idx) {
        bannerRepository.deleteById(idx);
    }


    //4. 추가
    public BannerDTO addBanner(BannerDTO bannerDto) {
        Banner newBanner = new Banner(bannerDto.getIdx(), bannerDto.getName(), bannerDto.getImageUrl());
        Banner savedBanner = bannerRepository.save(newBanner);
        return new BannerDTO(savedBanner.getIdx(), savedBanner.getName(), savedBanner.getImageUrl());
    }
    //5. 수정
    public BannerDTO updateBanner(int idx, BannerDTO updatedBannerDTO) {
        Banner bannerToUpdate = bannerRepository.findByIdx(idx).orElse(null);

        if (bannerToUpdate != null) {
            bannerToUpdate.setName(updatedBannerDTO.getName());
            bannerToUpdate.setImageUrl(updatedBannerDTO.getImageUrl());

            Banner updatedBanner = bannerRepository.save(bannerToUpdate);
            return convertEntityToDto(updatedBanner);
        } else {
            return null;
        }
    }

    private BannerDTO convertEntityToDto(Banner banner) {
        return new BannerDTO(banner.getIdx(), banner.getName(), banner.getImageUrl());
    }
}
