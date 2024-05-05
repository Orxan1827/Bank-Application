package az.spring.bankapplication.controller;

import az.spring.bankapplication.dto.request.OTPCodeRequest;
import az.spring.bankapplication.dto.request.ResetPasswordRequest;
import az.spring.bankapplication.dto.request.UserPasswordRequest;
import az.spring.bankapplication.service.userService.UserPasswordChangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OTPController {

    private final UserPasswordChangeService userPasswordChangeService;

    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(OK)
    @PostMapping("/resetPassword")
    public void resetPassword(@RequestHeader("Authorization") String auth, @Valid @RequestBody UserPasswordRequest request) {
        userPasswordChangeService.resetPassword(auth, request);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/otpCode")
    public ResponseEntity<String> verifyOtpCode(@RequestHeader("Authorization") String auth, @Valid @RequestBody OTPCodeRequest request) {
        return ResponseEntity.status(OK).body(userPasswordChangeService.enterOtpCode(auth, request));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/newPassword")
    public ResponseEntity<String> enterNewPassword(@RequestHeader("Authorization") String auth, @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.status(OK).body(userPasswordChangeService.enterNewPassword(auth, resetPasswordRequest));
    }

}
