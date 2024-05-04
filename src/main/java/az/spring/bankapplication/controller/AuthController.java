package az.spring.bankapplication.controller;

import az.spring.bankapplication.dto.request.UserLoginRequest;
import az.spring.bankapplication.dto.request.UserRegisterRequest;
import az.spring.bankapplication.dto.response.TokenResponse;
import az.spring.bankapplication.dto.response.UserRegisterResponse;
import az.spring.bankapplication.service.userService.UserLoginService;
import az.spring.bankapplication.service.userService.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegisterService userRegisterService;

    private final UserLoginService userLoginService;

    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponse> signup(@Valid @RequestBody UserRegisterRequest registerRequest) {
        return ResponseEntity.status(CREATED).body(userRegisterService.signup(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        return ResponseEntity.status(OK).body(userLoginService.login(loginRequest));
    }

}
