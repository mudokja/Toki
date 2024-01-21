package com.toki.backend.dto.token;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccessTokenDto {
	String accessToken;
}
