package rest_api_app.Services.AuthModule;

import Shared.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Models.User.User;
import rest_api_app.Repository.UserRepository.UserRepository;
import rest_api_app.Services.AuthModule.Dtos.LoginResponseDto;
import rest_api_app.Services.AuthModule.Dtos.UserLoginDto;
import rest_api_app.Services.AuthModule.Dtos.UserRegisterDto;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterDto input){
        if(userRepository.existByUsername((input.getUsername()))) {
            throw new UserFriendlyException(ErrorCode.UsernameAlreadyExist);
        }
        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()), input.getEmail(), input.getAddress(), input.getPhone());
        userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(UserLoginDto input) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(input.getUsername()));
        if(user.isEmpty()) {
            throw new UserFriendlyException(ErrorCode.UserNotFound);
        }
        var userGetted = user.get();
        var checkPassword = passwordEncoder.encode(input.getPassword()).equals(userGetted.getPassword());
        if(!checkPassword) {
            throw new UserFriendlyException(ErrorCode.PasswordIncorrect);
        }

        var token = new JwtUtil().generateToken(input.getUsername());
        return new LoginResponseDto(token, null);
    }
}
