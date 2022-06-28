package tr.com.tubitak.etkinlikyonetimsistemi.util;

public class ErrorType {
    public static final String ACTIVITY_NOT_FOUND = "Activity does not exist.";
    public static final String ACTIVITY_QUOTA_IS_FULL = "Cannot register due to quota restrictions.";
    public static final String CUSTOMER_ALREADY_REGISTERED = "Cannot register. Customer already registered.";
    public static final String INVALID_START_END_DATE = "Cannot create activity, end date is before start date.";
    public static final String ACTIVITY_ALREADY_STARTED = "Cannot update activity, activity already started.";
    public static final String USER_NOT_FOUND = "User does not exist.";
    public static final String USER_ALREADY_EXIST = "User already registered exist.";

    public static final String INVALID_TCID = "TC ID is invalid.";
}
