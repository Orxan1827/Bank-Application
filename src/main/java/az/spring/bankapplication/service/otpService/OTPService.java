package az.spring.bankapplication.service.otpService;

import az.spring.bankapplication.dto.OTPDto;
import az.spring.bankapplication.exception.OTPAllReadyUsedException;
import az.spring.bankapplication.exception.OTPCodeException;
import az.spring.bankapplication.exception.OTPCodeExpiredException;
import az.spring.bankapplication.exception.OTPCodeInvalidException;
import az.spring.bankapplication.util.GenerateCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static az.spring.bankapplication.enums.OTPStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    private static final String OTP_KEY_PREFIX = "otp:";

    private static final Duration EXPIRATION_DURATION = Duration.ofMinutes(10);

    private static int count;

    public String generateOTPCode(String username) {
        String code = GenerateCodeUtil.generateCode();
        OTPDto otpDto = OTPDto
                .builder()
                .otpCode(code)
                .username(username)
                .attempts(0)
                .creationTime(System.currentTimeMillis())
                .status(ACTIVE)
                .build();
        count = 0;
        String key = OTP_KEY_PREFIX + username;
        log.info("{}", otpDto.toString());
        log.info("{}", key);
        redisTemplate.opsForValue().set(key, otpDto, EXPIRATION_DURATION);
        return otpDto.getOtpCode();
    }

    public void verifyOTPCode(String username, String code) {
        String key = OTP_KEY_PREFIX + username;
        log.info("Verify key: {}", key);

        long currentTime = System.currentTimeMillis();


        OTPDto otpDto = (OTPDto) redisTemplate.opsForValue().get(key);

        log.info("otpDto:{},{},{},{}", otpDto, username, code, key);
        log.info("count: {}", count);
//        if (otpDto!=null) {
//            int attempts = otpDto.getAttempts();
//        }

        if (count > 4) {

            throw new OTPCodeException();
        }
        if (otpDto != null && otpDto.getOtpCode().equals(code) && otpDto.getStatus() == USED) {
            count++;
            throw new OTPAllReadyUsedException();
        }
        if (otpDto != null && otpDto.getOtpCode().equals(code) && otpDto.getStatus() == EXPIRED) {
            count++;
            throw new OTPCodeExpiredException();
        }
        if (otpDto != null && otpDto.getOtpCode().equals(code) && currentTime - otpDto.getCreationTime() > 30000 && otpDto.getStatus() != USED) {
            count++;
            otpDto.setStatus(EXPIRED);
            redisTemplate.opsForValue().set(key, otpDto);
            log.info("otpDto status updated to EXPIRED: {}", otpDto.getStatus());
            throw new OTPCodeExpiredException();
        }
        if (otpDto != null && !otpDto.getOtpCode().equals(code)) {
            count++;
            throw new OTPCodeInvalidException();
        }
        if (otpDto == null) {
            count++;
            throw new OTPCodeInvalidException();
        }
        otpDto.setStatus(USED);
        log.info("otpDto status updated to USED: {}", otpDto.getStatus());
        redisTemplate.opsForValue().set(key, otpDto);
    }

}
