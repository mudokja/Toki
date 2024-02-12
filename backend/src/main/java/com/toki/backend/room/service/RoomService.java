package com.toki.backend.room.service;

import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.room.dto.RoomInfoDto;
import com.toki.backend.room.dto.request.CreateRoomRequestDto;
import com.toki.backend.room.dto.response.CreateRoomResponseDto;
import com.toki.backend.room.dto.response.RoomListByCategoryDto;
import com.toki.backend.room.entity.Category;
import com.toki.backend.room.entity.Room;
import com.toki.backend.room.entity.RoomMember;
import com.toki.backend.room.repository.CategoryRepository;
import com.toki.backend.room.repository.RoomMemberRepository;
import com.toki.backend.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;


    public Page<RoomInfoDto> getRoomList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        return roomRepository.findAll(pageRequest);
    }


    public RoomListByCategoryDto getRoomListByCategoryPk(int categoryPk, int page) {
        PageRequest pageRequest = PageRequest.of(page, categoryPk);
        Page<RoomInfoDto> rooms = roomRepository.findAllByCategoryPk(pageRequest, categoryPk);

        Category category = categoryRepository.findById(categoryPk)
                        .orElseThrow(
                                NullPointerException::new
                        );

        return RoomListByCategoryDto.builder()
                .categoryPk(categoryPk)
                .categoryName(category.getName())
                .rooms(rooms)
                .build();
    }

    public Room getRoomByRoomPk(int roomPk) {
        return roomRepository.findById(roomPk).orElse(null);
    }

    public RoomMember getRoomMembersByRoomPk(int roomPk) {
        return roomMemberRepository.findById(roomPk).orElse(null);
    }

    public void updateRoomMember(int roomPk, String userPk) {
        RoomMember room = getRoomMembersByRoomPk(roomPk);
        roomMemberRepository.save(room);
    }


    @Transactional
    public CreateRoomResponseDto saveRoom(CreateRoomRequestDto createRoomRequestDto, String hostUserPk) {


        Room room = Room.builder()
                .parentRoomPk(roomRepository.findById(createRoomRequestDto.getParentRoomId()).orElse(null))
                .title(createRoomRequestDto.getRoomName())
                .category(categoryRepository.findById(createRoomRequestDto.getCategoryPk()).get())
                .isPrivate(createRoomRequestDto.getIsPrivate())
                .password(createRoomRequestDto.getRoomPassword())
                .build();

        roomRepository.save(room);

        Set<String> members = new HashSet<>();
        members.add(hostUserPk);

        RoomMember roomMember = RoomMember.builder()
                        .roomPk(room.getRoomPk())
                                .members(members)
                                        .build();

        roomMemberRepository.save(roomMember);

        return CreateRoomResponseDto.builder()
                .roomId(room.getRoomPk())
                .roomName(room.getTitle())
                .sessionId(room.getSessionId())
                .build();

    }
}
