package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.dto.profile.ProfileDto;
import com.ecommerce.controllers.handler.UserHandler;
import com.ecommerce.controllers.mappers.ProfileControllerMapper;
import com.ecommerce.domain.model.user.Email;
import com.ecommerce.domain.model.user.Info;
import com.ecommerce.domain.model.user.Profile;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserHandler userService;
    private final ProfileControllerMapper profileControllerMapper;

    public UserController(UserHandler userService, ProfileControllerMapper profileControllerMapper) {
        this.userService = userService;
        this.profileControllerMapper = profileControllerMapper;
    }

    @GetMapping("/profile")
    public ResponseEntity<Profile> getUserProfile() {

            return ResponseEntity.ok(userService.getUserProfile());
    }

    @PostMapping("/info")
    public ResponseEntity<ProfileDto> updateUserInfo(@Valid @RequestBody Info info) {
        Profile  profile= userService.updateUserInfo(info);
        ProfileDto dto = profileControllerMapper.toProfile(profile);
            return ResponseEntity.ok(dto);
    }

    @PostMapping("/email")
    public ResponseEntity<ProfileDto> updateUserEmail(@Valid @RequestBody Email email) {
        Profile  profile = userService.updateUserEmail(email);
        ProfileDto dto = profileControllerMapper.toProfile(profile);
            return ResponseEntity.ok(dto);
    }

}
