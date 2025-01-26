package com.sokheng.rento.rento_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sokheng.rento.rento_server.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByAuthToken(String authToken);
    boolean existsByEmail(String email);
    UserEntity findByUserId(String userId);
}