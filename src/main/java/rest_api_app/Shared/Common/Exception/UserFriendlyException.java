package rest_api_app.Shared.Common.Exception;

import lombok.Getter;

@Getter
public class UserFriendlyException extends RuntimeException{
    private final int errorCode;
    private final Object additionalData;

    public UserFriendlyException(int errorCode) {
        this.errorCode = errorCode;
        this.additionalData = null;
    }

    public UserFriendlyException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.additionalData = null;
    }

    public UserFriendlyException(int errorCode, Object additionalData) {
        this.errorCode = errorCode;
        this.additionalData = additionalData;
    }

    public UserFriendlyException(int errorCode, String message, Object additionalData) {
        super(message);
        this.errorCode = errorCode;
        this.additionalData = additionalData;
    }

}
