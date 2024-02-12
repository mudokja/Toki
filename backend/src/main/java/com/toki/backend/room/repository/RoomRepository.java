package com.toki.backend.room.repository;

import com.toki.backend.room.dto.RoomInfoDto;
import com.toki.backend.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select " +
            "new com.toki.backend.room.dto.RoomInfoDto(r.title, r.category.categoryPk, r.category.name, r.isPrivate)" +
            "from Room r " +
            "where r.category.categoryPk=:categoryPk")
    Page<RoomInfoDto> findAllByCategoryPk(PageRequest pageRequest, int categoryPk);

    @Query("select " +
            "new com.toki.backend.room.dto.RoomInfoDto(r.title, r.category.categoryPk, r.category.name, r.isPrivate)" +
            "from Room r ")
    Page<RoomInfoDto> findAll(PageRequest pageRequest);
}
