package com.toki.backend.banner.dto;

import lombok.*;

@Getter
@Setter
@ToString ///1111
@NoArgsConstructor
public class BannerDTO {

    private int idx;
    private String name;
    private String imageUrl;

    @Builder
    public BannerDTO(int idx, String name, String imageUrl) {
        this.idx = idx;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
