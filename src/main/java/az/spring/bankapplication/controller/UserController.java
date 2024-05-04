package az.spring.bankapplication.controller;

import az.spring.bankapplication.dto.request.*;
import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.dto.response.UserUpdateResponse;
import az.spring.bankapplication.service.userService.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserReadAllService userReadAllService;

    private final UserUpdateService userUpdateService;

    private final UserDeleteService userDeleteService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserReadResponse>> getAllUser() {
        return ResponseEntity.status(OK).body(userReadAllService.getAllUser());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable(name = "user-id") Long userId, UserUpdateRequest updateRequest) {
        return ResponseEntity.status(CREATED).body(userUpdateService.updateUser(userId, updateRequest));
    }

    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(OK)
    @DeleteMapping("/{user-id}")
    public void deleteUser(@PathVariable(name = "user-id") Long userId) {
        userDeleteService.deleteUser(userId);
    }

}
