package rest_api_app.Services.AuthModule.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
}
