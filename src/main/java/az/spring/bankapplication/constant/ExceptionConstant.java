package az.spring.bankapplication.constant;

public class ExceptionConstant {

    private ExceptionConstant() {

    }

    public static final String USER_NOT_FOUND = "User not found with given id";

    public static final String USER_NOT_FOUND_BY_EMAIL = "User not found with given email";

    public static final String USER_EMAIL_NOT_SAME_MESSAGE = "Enter your email correctly";

    public static final String USER_NOT_FOUND_BY_USERNAME = "User not found with given username";

    public static final String USER_ALREADY_EXISTS = "This pinCode is already used";

    public static final String SAME_ACCOUNT_NUMBER = "Account number is same";

    public static final String ACCOUNT_IS_INACTIVE = "Account is not active";

    public static final String ACCOUNT_NOT_FOUND = "Account not found";

    public static final String BALANCE_IS_NOT_ENOUGH = "There is not enough money in your account";

    public static final String BAD_CREDENTIAL = "Invalid Username or Password";

    public static final String RESET_CODE_EXCEPTION_MESSAGE = "The code you entered is not valid";

    public static final String OTP_INVALID_MESSAGE = "Invalid code!..";

    public static final String OTP_EXPIRED_MESSAGE = "Code expired";

    public static final String OTP_UPDATE_MESSAGE = "Update OTP";

    public static final String OTP_USED_MESSAGE = "Code already used 1 time";

}
