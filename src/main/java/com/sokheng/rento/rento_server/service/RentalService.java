package com.sokheng.rento.rento_server.service;

import com.sokheng.rento.rento_server.entity.RentalEntity;
import com.sokheng.rento.rento_server.repository.RentalRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {

    @Autowired
    private RentalRespository rentalRepository;

    public RentalEntity createRental(RentalEntity rental) {
        rental.setRentalId(UUID.randomUUID().toString());
        return rentalRepository.save(rental);
    }

    public RentalEntity updateRental(String rentalId, RentalEntity updatedRental) {
        Optional<RentalEntity> existingRental = rentalRepository.findById(rentalId);
        if (existingRental.isPresent()) {
            RentalEntity rental = existingRental.get();
            rental.setName(updatedRental.getName());
            rental.setPrice(updatedRental.getPrice());
            rental.setLocation(updatedRental.getLocation());
            rental.setDescription(updatedRental.getDescription());
            rental.setStatus(updatedRental.getStatus());
            rental.setPropertyType(updatedRental.getPropertyType());
            rental.setImageUrl(updatedRental.getImageUrl());
            return rentalRepository.save(rental);
        }
        return null;
    }

    public void deleteRental(String rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    public List<RentalEntity> getAllRentals() {
        return rentalRepository.findAll();
    }

    public RentalEntity getRentalById(String rentalId) {
        return rentalRepository.findById(rentalId).orElse(null);
    }

    public List<RentalEntity> getRentalsByAdminId(String adminId) {
        return rentalRepository.findByAdminId(adminId);
    }

    public List<RentalEntity> getRentalsByCustomerId(String customerId) {
        return rentalRepository.findByCustomerId(customerId);
    }
}
