package rest_api_app.Services.AuthModule;

import rest_api_app.Models.User.User;
import rest_api_app.Services.AuthModule.Dtos.LoginResponseDto;
import rest_api_app.Services.AuthModule.Dtos.UserLoginDto;
import rest_api_app.Services.AuthModule.Dtos.UserRegisterDto;

public interface AuthService {
    public void register(UserRegisterDto input);
    public LoginResponseDto login(UserLoginDto input);
}
