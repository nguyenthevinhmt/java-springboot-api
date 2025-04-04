package rest_api_app.Shared.Common.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rest_api_app.Shared.Common.Api.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final LocalizeService localizeService;

    public GlobalExceptionHandler(LocalizeService localizeService) {
        this.localizeService = localizeService;
    }

    @ExceptionHandler(UserFriendlyException.class)
    public ResponseEntity<ApiResponse> handleUserFriendlyException(UserFriendlyException ex, HttpServletRequest request) {
        String language = getLanguageFromRequest(request);
        String message = ex.getMessage();

        // Nếu không có message cụ thể, lấy từ file ngôn ngữ
        if (message == null || message.isEmpty()) {
            message = localizeService.getMessage(ex.getErrorCode(), language);
        }

        ApiResponse response = new ApiResponse().Error(ex.getErrorCode(), message);

        // Luôn trả về status 200 nhưng thông tin lỗi trong body
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
        String language = getLanguageFromRequest(request);
        String message = localizeService.getMessage(ErrorCode.INTERNAL_SERVER_ERROR, language);

        ApiResponse response = new ApiResponse().Error(ErrorCode.INTERNAL_SERVER_ERROR, message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String getLanguageFromRequest(HttpServletRequest request) {
        // Lấy ngôn ngữ từ Accept-Language header
        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage != null && !acceptLanguage.isEmpty()) {
            // Chỉ lấy phần đầu tiên (vd: en-US -> en)
            return acceptLanguage.split(",")[0].split("-")[0];
        }
        return "en"; // Mặc định là tiếng Anh
    }
}