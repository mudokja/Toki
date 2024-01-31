package com.toki.backend.badge;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class BadgeController {



    private final BadgeService badgeService;



    //1, 모든 배지를 조회한다.
    @GetMapping("/api/v1/badges")
    public ResponseEntity<List<BadgeDTO>> getAllBadges() {
        List<BadgeDTO> badges = badgeService.getAllBadges();
        System.out.println("DATA FROM FE : " + badges +  " received");
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    ///2 . 배지 등록

    @PostMapping("/api/v1/admin/badges")
    public ResponseEntity<BadgeDTO> saveBadge(@RequestBody BadgeDTO badgeDTO) {
        BadgeDTO newBadge = badgeService.saveBadge(badgeDTO);
//        return new ResponseEntity<>(newBadge, HttpStatus.CREATED);
        System.out.println("DATA FROM FE : " + newBadge +  " received");
        return ResponseEntity.ok().build(); //임시로 확인용
    }

}
