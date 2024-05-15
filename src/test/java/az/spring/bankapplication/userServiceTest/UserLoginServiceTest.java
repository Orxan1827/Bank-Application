package az.spring.bankapplication.userServiceTest;

import az.spring.bankapplication.dto.request.UserLoginRequest;
import az.spring.bankapplication.dto.response.TokenResponse;
import az.spring.bankapplication.exception.CustomBadCredentialsException;
import az.spring.bankapplication.service.tokenService.JwtTokenService;
import az.spring.bankapplication.service.userService.UserLoginService;
import az.spring.bankapplication.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static az.spring.bankapplication.util.UserUtil.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserLoginServiceTest {

    @Mock
    private  JwtTokenService tokenService;

    @Mock
    private  AuthenticationManager authenticationManager;

    @InjectMocks
    private UserLoginService userLoginService;

    @Test
    public void testUserLoginService_whenUserLoginRequestTrue_shouldReturnTokenResponse() {
        UserLoginRequest request = userLoginRequest();
        TokenResponse expect = tokenResponse();
        Authentication auth = new UsernamePasswordAuthenticationToken("test_username", "test_password");

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))).thenReturn(auth);
        when(tokenService.generateToken(auth)).thenReturn("test_token");

        TokenResponse actual = userLoginService.login(request);

        assertNotNull(actual);
        Assertions.assertEquals(actual, expect);

        verify(authenticationManager,times(1)).authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        verify(tokenService, times(1)).generateToken(auth);
    }

    @Test
    public void testUserLoginService_whenUserLoginRequestFalse_shouldReturnTokenResponse() {
        UserLoginRequest request = userLoginRequest();
        TokenResponse expect = tokenResponse();
        Authentication auth = new UsernamePasswordAuthenticationToken("test_username", "test_password");

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))).thenThrow(CustomBadCredentialsException.class);
        when(tokenService.generateToken(auth)).thenReturn("test_token");

        assertThrows(CustomBadCredentialsException.class, () -> userLoginService.login(request));

        verify(authenticationManager,times(1)).authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        verify(tokenService, never()).generateToken(auth);
    }

}
