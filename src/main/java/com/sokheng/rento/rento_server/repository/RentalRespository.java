package com.sokheng.rento.rento_server.repository;

import com.sokheng.rento.rento_server.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRespository extends JpaRepository<RentalEntity, String> {
    List<RentalEntity> findByAdminId(String adminId);
    List<RentalEntity> findByCustomerId(String customerId);
}
