package com.sokheng.rento.rento_server.api;

import com.sokheng.rento.rento_server.entity.RentalEntity;
import com.sokheng.rento.rento_server.service.RentalService;
import com.sokheng.rento.rento_server.entity.ApiResponse;
import com.sokheng.rento.rento_server.service.UserService;
import com.sokheng.rento.rento_server.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<RentalEntity> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping("/create")
    public ApiResponse postCreateRental(@RequestParam String authToken, @RequestParam String name, @RequestParam float price, @RequestParam String location, @RequestParam String publishDate,
    @RequestParam String expireDate, @RequestParam String description, @RequestParam String status, @RequestParam String propertyType, @RequestParam String imageUrl) {
        // Check if authToken is null or empty
        if (authToken == null || authToken.trim().isEmpty()) {
            return new ApiResponse("Authentication token is required", "fail");
        }
        
        // Find user by auth token
        UserEntity userEntity = userService.findUserByAuthToken(authToken);
        
        // Check if user is null
        if (userEntity == null) {
            return new ApiResponse("Invalid authentication token", "fail");
        }
        
        // Check user role using .equals() instead of ==
        if (userEntity.getRole() == null || !userEntity.getRole().equals("admin")) {
            return new ApiResponse("Only admin can create rental", "fail");
        }
        
        // Create rental
        rentalService.createRental(new RentalEntity(UUID.randomUUID().toString(), name, userEntity.getUserId(), price, location, description, publishDate, expireDate, status, propertyType, "", imageUrl));
        return new ApiResponse("Rental created successfully", "success");
    }

    @PostMapping("/delete")
    public ApiResponse postDeleteRental(@RequestParam String authToken, @RequestParam String rentalId) {
        // Check if authToken is null or empty
        if (authToken == null || authToken.trim().isEmpty()) {
            return new ApiResponse("Authentication token is required", "fail");
        }
        
        // Find user by auth token
        UserEntity userEntity = userService.findUserByAuthToken(authToken);
        
        // Check if user is null
        if (userEntity == null) {
            return new ApiResponse("Invalid authentication token", "fail");
        }
        
        // Check user role using .equals() instead of ==
        if (userEntity.getRole() == null || !userEntity.getRole().equals("admin")) {
            return new ApiResponse("Only admin can delete rental", "fail");
        } else {
            rentalService.deleteRental(rentalId);
            return new ApiResponse("Rental deleted successfully", "success");
        }
    }

    @PostMapping("/purchase")
    public ApiResponse postPurchaseRental(@RequestParam String rentalId, @RequestParam String authToken) {
        // Check if authToken is null or empty
        if (authToken == null || authToken.trim().isEmpty()) {
            return new ApiResponse("Authentication token is required", "fail");
        }
        
        // Find user by auth token
        UserEntity userEntity = userService.findUserByAuthToken(authToken);
        
        // Check if user is null
        if (userEntity == null) {
            return new ApiResponse("Invalid authentication token", "fail");
        }
        
        if (userEntity.getRole() == "admin") {
            return new ApiResponse("Admin cannot purchase rental", "fail");
        }
        RentalEntity rentalEntity = rentalService.getRentalById(rentalId);
        if (rentalEntity.getCustomerId().length() > 2) {
            return new ApiResponse("This rental has already been purchased", "fail");
        } else {
            rentalEntity.setCustomerId(userEntity.getUserId());
            rentalEntity.setStatus("Occupied");
            rentalService.updateRental(rentalId, rentalEntity);
            return new ApiResponse("This rental has been purchased", "success");
        }
    }
    
}
