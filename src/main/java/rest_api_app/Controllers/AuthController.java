package rest_api_app.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rest_api_app.Config.TokenService;
import rest_api_app.Dtos.AuthenticationDto.RegisterDto;
import rest_api_app.Dtos.AuthenticationDto.UserLoginDto;
import rest_api_app.Dtos.AuthenticationDto.UserLoginResultDto;
import rest_api_app.Models.User.User;
import rest_api_app.Repository.UserRepository.UserRepository;
import rest_api_app.Shared.Common.Api.ApiResponse;
import rest_api_app.Shared.Common.Exception.ErrorCode;
import rest_api_app.Shared.Common.Exception.UserFriendlyException;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates user login.
     *
     * @param data Object containing user credentials
     * @return ResponseEntity containing authentication token
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse login(@RequestBody UserLoginDto data) {
        var credentials = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(credentials);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new ApiResponse(new UserLoginResultDto(token, null));
    }

    /**
     * Registers a new user.
     *
     * @param data Object containing user registration data
     * @return ResponseEntity indicating success or failure of registration
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse register(@RequestBody RegisterDto data) {
        if (this.userRepository.findByUsername(data.getUsername()).isPresent()){
            throw new RuntimeException("JAVA ngu");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User user = new User(data.getUsername(), encryptedPassword, data.getEmail(), data.getAddress(), data.getPhone());

        this.userRepository.save(user);

        return new ApiResponse();
    }
}
