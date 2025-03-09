package com.toki.backend.common.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Builder
@ToString
public class CommonResponseDto<T extends Object> {
    private Integer resultCode;
    private String resultMessage;
    private T data;

    private String referenceUri; // option 선택사항, 필요한 자원을 얻기위한 참조 api 주소 첨부

}
