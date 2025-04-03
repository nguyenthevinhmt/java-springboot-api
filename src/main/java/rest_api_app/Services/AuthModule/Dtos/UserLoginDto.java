package rest_api_app.Services.AuthModule.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginDto {
    public String username;
    public String password;
}
