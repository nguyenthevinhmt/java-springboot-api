package rest_api_app.Shared.Common.Exception;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class ErrorCode {
    //Default error code
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int CONFLICT = 409;
    public static final int TOO_MANY_REQUEST = 429;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int UNKNOWN_ERROR = 9999;

    //Custom error code
    public static final int ProductNotFound = 1000;

    //
    public static final int UsernameAlreadyExist = 2000;
    public static final int UserNotFound = 2001;
    public static final int PasswordIncorrect = 2002;

    // Mapping tên biến -> giá trị
    private static final Map<String, Integer> nameToCodeMap = new HashMap<>();
    // Mapping giá trị -> tên biến
    private static final Map<Integer, String> codeToNameMap = new HashMap<>();

    static {
        // Sử dụng reflection để lấy tất cả các hằng số
        Field[] fields = ErrorCode.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())
                    && field.getType() == int.class) {
                try {
                    String name = field.getName();
                    Integer value = field.getInt(null);
                    nameToCodeMap.put(name, value);
                    codeToNameMap.put(value, name);
                } catch (IllegalAccessException e) {
                    // Xử lý ngoại lệ
                }
            }
        }
    }

    public static String getErrorName(int code) {
        return codeToNameMap.getOrDefault(code, "UNKNOWN_ERROR");
    }

    public static int getErrorCode(String name) {
        return nameToCodeMap.getOrDefault(name, UNKNOWN_ERROR);
    }
}
