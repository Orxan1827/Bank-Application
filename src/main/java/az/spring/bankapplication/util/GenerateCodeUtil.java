package az.spring.bankapplication.util;

import java.util.Random;

public class GenerateCodeUtil {

    public static String generateCode() {
        int length = 6;
        String num = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            otp.append(num.charAt(random.nextInt(num.length())));
        }
        return otp.toString();
    }

}
