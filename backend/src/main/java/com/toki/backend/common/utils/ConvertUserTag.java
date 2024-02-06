package com.toki.backend.common.utils;

public class ConvertUserTag {

    // 유저 태그를 16진수로 변환하고 다시 10진수로 변환하는 메서드
    /* toHex가 true이면 10진수를 16진수로 변환
       toHex가 false이면 16진수를 10진수로 변환 */
    public String convertUserTag(int userTag) {
        return Integer.toHexString(userTag);
    }

    public Integer convertUserTag(String userTag) {
        return Integer.parseInt(userTag,16);
    }
}
