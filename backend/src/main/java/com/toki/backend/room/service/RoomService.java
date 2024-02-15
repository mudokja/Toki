package com.toki.backend.room.service;


import com.toki.backend.room.dto.RoomInfoDto;
import com.toki.backend.room.dto.request.CreateRoomRequestDto;
import com.toki.backend.room.dto.response.CreateRoomResponseDto;
import com.toki.backend.room.entity.Category;
import com.toki.backend.room.entity.Room;
import com.toki.backend.room.entity.RoomMember;
import com.toki.backend.room.entity.RoomTag;
import com.toki.backend.room.repository.CategoryRepository;
import com.toki.backend.room.repository.RoomMemberRepository;
import com.toki.backend.room.repository.RoomRepository;
import com.toki.backend.room.repository.RoomTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final RoomTagRepository roomTagRepository;


    public Page<RoomInfoDto> getRoomList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        Page<Room> roomPage = roomRepository.findAll(pageRequest);

        return getRoomInfoDtos(roomPage);
    }


    public Page<RoomInfoDto> getRoomListByCategoryPk(int categoryPk, int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        Category category = categoryRepository.findById(categoryPk).get();
        Page<Room> roomPage = roomRepository.findAllByCategory(pageRequest, category);

        return getRoomInfoDtos(roomPage);
    }

    @Transactional
    public Stream<RoomInfoDto> getRoomListByTag(String tagName) {
        List<RoomTag> roomTags = roomTagRepository.findAllByTags(tagName);
        List<String> roomTagsPk = roomTags.stream().map(RoomTag::getRoomPk).toList();
        List<Room> rooms = roomRepository.findAllById(roomTagsPk);

        return rooms.stream().map(room -> {
            Integer currentCount = roomMemberRepository.findById(room.getRoomPk())
                    .orElse(new RoomMember(room.getRoomPk(), new HashSet<>()))
                    .getMembers()
                    .size();
            Set<String> tags = roomTagRepository.findById(room.getRoomPk())
                    .orElse(new RoomTag(room.getRoomPk(), new HashSet<>()))
                    .getTags();

            return RoomInfoDto.builder()
                    .roomName(room.getTitle())
                    .categoryPk(room.getCategory().getCategoryPk())
                    .categoryName(room.getCategory().getName())
                    .isPrivate(room.getIsPrivate())
                    .currentCount(currentCount)
                    .tags(tags)
                    .build();
        });
    }

    private Page<RoomInfoDto> getRoomInfoDtos(Page<Room> roomPage) {
        return roomPage.map(room -> {
            Integer currentCount = roomMemberRepository.findById(room.getRoomPk())
                    .orElse(new RoomMember(room.getRoomPk(), new HashSet<>()))
                    .getMembers()
                    .size();
            Set<String> tags = roomTagRepository.findById(room.getRoomPk())
                    .orElse(new RoomTag(room.getRoomPk(), new HashSet<>()))
                    .getTags();

            return RoomInfoDto.builder()
                    .roomName(room.getTitle())
                    .categoryPk(room.getCategory().getCategoryPk())
                    .categoryName(room.getCategory().getName())
                    .isPrivate(room.getIsPrivate())
                    .currentCount(currentCount)
                    .tags(tags)
                    .build();
        });
    }

    public Room getRoomByRoomPk(String roomPk) {
        return roomRepository.findById(roomPk).orElse(null);
    }

    public RoomMember getRoomMembersByRoomPk(String roomPk) {
        return roomMemberRepository.findById(roomPk).orElse(null);
    }

    public RoomTag getRoomTagByRoomPk(String roomPk) {
        return roomTagRepository.findById(roomPk).orElse(null);
    }

    public void addRoomMember(String roomPk, String userPk) {
        RoomMember room = getRoomMembersByRoomPk(roomPk);
        room.getMembers().add(userPk);
        roomMemberRepository.save(room);
    }

    public void deleteRoomMember(String roomPk, String userPk) {
        RoomMember room = getRoomMembersByRoomPk(roomPk);
        room.getMembers().remove(userPk);
        roomMemberRepository.save(room);
    }

    public void updateRoomTag(String roomPk, Set<String> tags) {
        RoomTag room = getRoomTagByRoomPk(roomPk);
        room.setTags(tags);
        roomTagRepository.save(room);
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

        Set<String> tags = createRoomRequestDto.getTags();

        RoomTag roomTag = RoomTag.builder()
                .roomPk(room.getRoomPk())
                .tags(tags)
                .build();

        roomTagRepository.save(roomTag);

        return CreateRoomResponseDto.builder()
                .roomId(room.getRoomPk())
                .roomName(room.getTitle())
                .sessionId(room.getSessionId())
                .build();

    }
}
