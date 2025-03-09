package com.toki.backend.banner.service;


import com.toki.backend.banner.dto.BannerDTO;
import com.toki.backend.banner.entity.Banner;
import com.toki.backend.banner.repository.BannerRepository;
import com.toki.backend.common.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    // 1. 모두 조회
    public CommonResponseDto<List<BannerDTO>> getAllBanners() {
        try {
            List<Banner> banners = bannerRepository.findAll();
            List<BannerDTO> bannerDTO = banners.stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList());

            return CommonResponseDto.<List<BannerDTO>>builder() //성공한 경우
                    .resultCode(200)
                    .resultMessage("조회에 성공하였습니다.")
                    .data(bannerDTO)
                    .build();
        } catch (Exception e) { //실패한 경우
            return CommonResponseDto.<List<BannerDTO>>builder()
                    .resultCode(400)
                    .resultMessage("조회에 실패하였습니다.")
                    .build();
        }
    }

    // 2. 특정 인덱스만 조회
    public CommonResponseDto<BannerDTO> findByIdx(int idx) {
        try {
            Banner banner = bannerRepository.findByIdx(idx).orElse(null); //null값 처리하기 위함

            if (banner != null) {
                BannerDTO bannerDTO = convertEntityToDto(banner);
                return CommonResponseDto.<BannerDTO>builder()
                        .resultCode(200)
                        .resultMessage("조회에 성공하였습니다.")
                        .data(bannerDTO)
                        .build();
            } else {
                return CommonResponseDto.<BannerDTO>builder()
                        .resultCode(404)
                        .resultMessage("찾을 수 없는 배너입니다.")
                        .build();
            }
        } catch (Exception e) {

            return CommonResponseDto.<BannerDTO>builder()
                    .resultCode(400)
                    .resultMessage("조회에 실패하였습니다.")
                    .build();
        }
    }

    // 3. 삭제
    public CommonResponseDto<Void> deleteBanner(int idx) {
        try {
            bannerRepository.deleteById(idx);
            return CommonResponseDto.<Void>builder()
                    .resultCode(200)
                    .resultMessage("삭제에 성공하였습니다.")
                    .build();
        } catch (Exception e) {
            // 예외 처리 또는 로깅 추가
            return CommonResponseDto.<Void>builder()
                    .resultCode(400)
                    .resultMessage("삭제에 실패하였습니다.")
                    .build();
        }
    }


    // 4. 추가
    public CommonResponseDto<BannerDTO> addBanner(BannerDTO bannerDto) {
        try {
            Banner newBanner = new Banner(bannerDto.getIdx(), bannerDto.getName(), bannerDto.getImageUrl());
            Banner savedBanner = bannerRepository.save(newBanner);

            return CommonResponseDto.<BannerDTO>builder()
                    .resultCode(200)
                    .resultMessage("베너 추가에 성공하였습니다.")
                    .data(convertEntityToDto(savedBanner))
                    .build();
        } catch (Exception e) {
            return CommonResponseDto.<BannerDTO>builder()
                    .resultCode(400)
                    .resultMessage("배너 추가에 실패하였습니다.")
                    .build();
        }
    }

    // 5. 수정
    public CommonResponseDto<BannerDTO> updateBanner(int idx, BannerDTO updatedBannerDTO) {
        try {
            Banner bannerToUpdate = bannerRepository.findByIdx(idx).orElse(null);

            if (bannerToUpdate != null) {
                bannerToUpdate.setName(updatedBannerDTO.getName());
                bannerToUpdate.setImageUrl(updatedBannerDTO.getImageUrl());

                Banner updatedBanner = bannerRepository.save(bannerToUpdate);
                return CommonResponseDto.<BannerDTO>builder()
                        .resultCode(200)
                        .resultMessage("배너 수정에 성공하였습니다.")
                        .data(convertEntityToDto(updatedBanner))
                        .build();
            } else {
                return CommonResponseDto.<BannerDTO>builder()
                        .resultCode(400)
                        .resultMessage("배너를 찾을 수 없습니다.")
                        .build();
            }
        } catch (Exception e) {
            return CommonResponseDto.<BannerDTO>builder()
                    .resultCode(400)
                    .resultMessage("배너를 수정할 수 없습니다.")
                    .build();
        }
    }

    private BannerDTO convertEntityToDto(Banner banner) {
        return new BannerDTO(banner.getIdx(), banner.getName(), banner.getImageUrl());
    }
}
