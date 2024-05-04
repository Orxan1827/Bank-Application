package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.exception.GenericException;
import az.spring.bankapplication.dto.request.UserLoginRequest;
import az.spring.bankapplication.dto.response.TokenResponse;
import az.spring.bankapplication.service.tokenService.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static az.spring.bankapplication.constant.ExceptionConstant.BAD_CREDENTIAL;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final JwtTokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public TokenResponse login(UserLoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponse.builder()
                    .accessToken(tokenService.generateToken(auth))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .errorMessage(BAD_CREDENTIAL).build();
        }
    }

}
