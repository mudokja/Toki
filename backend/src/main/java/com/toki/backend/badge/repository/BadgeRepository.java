package com.toki.backend.badge.repository;

import com.toki.backend.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {

    Optional<Badge> findByIdx(int idx);
}
