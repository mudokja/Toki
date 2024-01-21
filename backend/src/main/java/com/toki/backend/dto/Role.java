package com.toki.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Role {
    USER,
    ADMIN,
    GUEST


}
