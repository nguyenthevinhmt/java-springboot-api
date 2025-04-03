package rest_api_app.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest_api_app.Common.Api.ApiResponse;
import rest_api_app.Services.AuthModule.AuthService;
import rest_api_app.Services.AuthModule.Dtos.UserLoginDto;
import rest_api_app.Services.AuthModule.Dtos.UserRegisterDto;
import rest_api_app.Services.UserModule.UserService;

@RestController
@RequestMapping(path = "/api/auth")
@Tag(name = "Auth API", description = "Auth API Controller")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ApiResponse Login(@RequestBody UserLoginDto request) {
        return new ApiResponse(authService.login(request));
    }

    @PostMapping("register")
    public ApiResponse Register(@RequestBody UserRegisterDto request) {
        authService.register(request);
        return new ApiResponse();
    }
}
