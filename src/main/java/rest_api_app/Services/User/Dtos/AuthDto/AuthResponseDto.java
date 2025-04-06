package rest_api_app.Services.User.Dtos.AuthDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private Object expiredDate;
}