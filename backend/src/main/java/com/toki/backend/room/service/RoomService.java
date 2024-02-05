package com.toki.backend.room.service;

import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.room.dto.request.CreateRoomRequestDto;
import com.toki.backend.room.dto.response.RoomListByCategoryDto;
import com.toki.backend.room.entity.Category;
import com.toki.backend.room.entity.Room;
import com.toki.backend.room.repository.CategoryRepository;
import com.toki.backend.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;


    public CommonResponseDto<Object> getRoomList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        Page<Room> rooms = roomRepository.findAll(pageRequest);

        return CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("방 목록 조회에 성공했습니다.")
                .data(rooms)
                .build();
    }

    // 해시태그 부분
//    public CommonResponseDto<Object> getRoomListByHashTag(String hashTag) {
//        List<Room> rooms = roomRepository.findAllByHashTag(hashTag);
//    }

    public CommonResponseDto<Object> getRoomListByCategoryPk(int categoryPk, int page) {
        PageRequest pageRequest = PageRequest.of(page, categoryPk);
        Page<Room> rooms = roomRepository.findAllByCategoryPk(pageRequest, categoryPk);

        Category category = categoryRepository.findById(categoryPk)
                        .orElseThrow(
                                NullPointerException::new
                        );

        RoomListByCategoryDto roomListByCategoryDto = RoomListByCategoryDto.builder()
                .categoryPk(categoryPk)
                .categoryName(category.getName())
                .rooms(rooms)
                .build();

        return CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("카테고리별 방 리스트 조회에 성공하였습니다.")
                .data(roomListByCategoryDto)
                .build();
    }

    @Transactional
    public CommonResponseDto<Object> saveRoom(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                              CreateRoomRequestDto requestDto) {

        return CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("방 저장에 성공하였습니다.")
                .data(null)
                .build();
    }
}
