package com.toki.backend.banner;



import com.toki.backend.banner.dto.BannerDTO;
import com.toki.backend.banner.entity.Banner;
import com.toki.backend.banner.repository.BannerRepository;
import com.toki.backend.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/banner")
public class BannerController {//1. 배너 전체 조회  2. 특정 배너 조회, 3. 삭제 4. 추가. 5.수정


    @Autowired
    private BannerService bannerService;



    //1. 배너 전체를 조회하기
    @GetMapping
    public ResponseEntity<List<BannerDTO>> getAllBanners() {
        List<BannerDTO> banners = bannerService.getAllBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }


    //2. 특정 배너를 조회하기
    @GetMapping("/{idx}")
    public ResponseEntity<?> getBannerByIdx(@PathVariable int idx) {
        List<?> bannerNames = bannerService.findByIdx(idx);
        return bannerNames.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(bannerNames, HttpStatus.OK);
    }

    //3. 삭제
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBanner(@PathVariable int idx) {
        bannerService.deleteBanner(idx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //4. 추가
    //
    @PostMapping
    public ResponseEntity<BannerDTO> addBanner(@RequestBody BannerDTO bannerDTO) {
        BannerDTO addedBanner = bannerService.addBanner(bannerDTO);
        return new ResponseEntity<>(addedBanner, HttpStatus.CREATED);
    }


    //5. 수정
    @PutMapping("/{idx}")
    public ResponseEntity<BannerDTO> updateBanner(@PathVariable int idx, @RequestBody BannerDTO updatedBannerDTO) {
        BannerDTO updatedBanner = bannerService.updateBanner(idx, updatedBannerDTO);
        return updatedBanner != null ? new ResponseEntity<>(updatedBanner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
