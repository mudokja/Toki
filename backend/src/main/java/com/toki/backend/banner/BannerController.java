package com.toki.backend.banner;



import com.toki.backend.banner.dto.BannerDTO;
import com.toki.backend.banner.entity.Banner;
import com.toki.backend.banner.repository.BannerRepository;
import com.toki.backend.banner.service.BannerService;
import com.toki.backend.common.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {//1. 배너 전체 조회  2. 특정 배너 조회, 3. 삭제 4. 추가. 5.수정



    private BannerService bannerService;



    // 모든 배너 조회
    @GetMapping
    public CommonResponseDto<List<BannerDTO>> getAllBanners() {
        return bannerService.getAllBanners();
    }


    // 특정 인덱스의 배너 조회
    @GetMapping("/{idx}")
    public CommonResponseDto<BannerDTO> getBannerByIdx(@PathVariable int idx) {
        return bannerService.findByIdx(idx);
    }


    // 특정 인덱스의 배너 삭제
    @DeleteMapping("/{idx}")
    public CommonResponseDto<Void> deleteBanner(@PathVariable int idx) {
        return bannerService.deleteBanner(idx);
    }

    // 새로운 배너 추가
    @PostMapping
    public CommonResponseDto<BannerDTO> addBanner(@RequestBody BannerDTO bannerDto) {
        return bannerService.addBanner(bannerDto);
    }


    // 특정 인덱스의 배너 수정
    @PutMapping("/{idx}")
    public CommonResponseDto<BannerDTO> updateBanner(@PathVariable int idx, @RequestBody BannerDTO updatedBannerDTO) {
        return bannerService.updateBanner(idx, updatedBannerDTO);
    }
}
