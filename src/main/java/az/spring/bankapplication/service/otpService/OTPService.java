package az.spring.bankapplication.service.otpService;

import az.spring.bankapplication.util.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPService {

    private static final long OTP_EXPIRATION = 60;

    private final RedisTemplate<String, String> redisTemplate;

    public String generateOTP() {
        String otp = GenerateCodeUtil.generateCode();
        String key = "OTP:" + otp;
        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRATION, TimeUnit.SECONDS);
        return otp;
    }

    public String verifyOTP(String otp) {
        String key = "OTP:" + otp;
        String storedOTP = redisTemplate.opsForValue().get(key);
        Long expTime = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        log.info("expTime: {}", expTime);
        log.info("storedOTP: {}", storedOTP);
        if (expTime != null && expTime <= 0 && expTime != -2) {
            return "OTPDto expired";
        }
        if (storedOTP != null && storedOTP.equals(otp)) {
            redisTemplate.delete(key);
            return "Successfully";
        }
        return "Invalid code!..";
    }

}
