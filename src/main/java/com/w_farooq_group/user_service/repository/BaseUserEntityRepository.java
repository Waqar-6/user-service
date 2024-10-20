package com.w_farooq_group.user_service.repository;

import com.w_farooq_group.user_service.entity.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface BaseUserEntityRepository extends JpaRepository<BaseUserEntity, UUID> {
    Boolean existsByEmail(String email);
    Optional<BaseUserEntity> findByEmail(String email);
}
