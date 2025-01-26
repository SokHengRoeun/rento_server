package com.sokheng.rento.rento_server.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sokheng.rento.rento_server.entity.UserEntity;
import com.sokheng.rento.rento_server.service.UserService;
import com.sokheng.rento.rento_server.entity.ApiResponse;
import com.sokheng.rento.rento_server.entity.ApiLoginResponse;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@RequestParam String email, @RequestParam String password) {
        UserEntity userEntity = userService.findByEmailAndPassword(email, password);

        if (userEntity == null) {
            return new ApiResponse("User not found", "fail");
        }

        String authToken = UUID.randomUUID().toString();
        userEntity.setAuthToken(authToken);
        userService.save(userEntity);

        ApiLoginResponse response = new ApiLoginResponse(authToken, userEntity.getRole(), "success", userEntity.getUserId(), userEntity.getName());
        return response;
    }

    @PostMapping("/register")
    public ApiResponse Register(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String address,
     @RequestParam String phoneNumber, @RequestParam String gender, @RequestParam String role) {
        // Check if email is already registered
        if (userService.isEmailAlreadyRegistered(email)) {
            return new ApiResponse("This email is already used", "fail");
        }

        String authToken = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        userService.createUser(new UserEntity(userId, email, password, name, address, phoneNumber, gender, role, authToken, ""));
        return new ApiResponse("User created successfully", "success");
    }

    @PostMapping("/logout")
    public ApiResponse Logout(@RequestParam String token) {
        UserEntity userEntity = userService.findUserByAuthToken(token);
        userEntity.setAuthToken("");
        return new ApiResponse("Logout successful", "success");
    }

    @PostMapping("/profile")
    public ApiResponse postProfile(@RequestParam String token, @RequestParam String profile) {
        UserEntity userEntity = userService.findUserByAuthToken(token);
        userEntity.setProfileImage(profile);
        return new ApiResponse("Profile successfully changed", "success");
    }

    @GetMapping("/username")
    public String getUsername(@RequestParam String userId) {
        UserEntity userEntity = userService.findByUserId(userId);
        return userEntity.getName();
    }
    
}
