package com.toki.backend.common.utils;

public class ConvertUserTag {

    // 유저 태그를 16진수로 변환하고 다시 10진수로 변환하는 메서드

    public static String convertUserTag(int userTag) {
        return Integer.toHexString(userTag);
    }

    public static Integer convertUserTag(String userTag) {
        return Integer.parseInt(userTag,16);
    }
}
