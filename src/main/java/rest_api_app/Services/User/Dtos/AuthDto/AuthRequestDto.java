package rest_api_app.Services.User.Dtos.AuthDto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
