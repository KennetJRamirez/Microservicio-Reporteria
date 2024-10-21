package com.umg.reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umg.reportes.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}