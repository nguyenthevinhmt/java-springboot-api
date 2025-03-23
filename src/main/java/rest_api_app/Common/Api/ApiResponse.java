package rest_api_app.Common.Api;

import lombok.*;

@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    @Builder.Default
    private int status = StatusCode.Success.ordinal();
    @Builder.Default
    private String message = null;
    @Builder.Default
    private Object data = null;
    @Builder.Default
    private int code = 200;

    public ApiResponse(Object data) {
        this();
        this.data = data;
    }

    public ApiResponse Error(int code, String message) {
        return new ApiResponse(StatusCode.Error.ordinal(), message, null, code);
    }

    public enum StatusCode {
        Error,
        Success

    }
}

