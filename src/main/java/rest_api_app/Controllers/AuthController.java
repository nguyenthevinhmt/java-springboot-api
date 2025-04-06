package rest_api_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import rest_api_app.Common.Api.ApiResponse;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Config.JwtUtils;
import rest_api_app.Repository.User.TokenManagerRepository;
import rest_api_app.Services.User.Dtos.AuthDto.AuthRequestDto;
import rest_api_app.Services.User.Dtos.AuthDto.AuthResponseDto;
import rest_api_app.Entity.User.AppUser;
import rest_api_app.Repository.User.UserRepository;
import rest_api_app.Services.User.Implements.TokenManagerService;
import rest_api_app.Shared.Constant.TokenTypeConst;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired private JwtUtils jwtUtil;
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenManagerService tokenManagerService;
    @Autowired private TokenManagerRepository tokenManagerRepository;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody AuthRequestDto request) {
        AppUser appUser = userRepo.findFirstByUsername(request.getUsername()).orElseThrow(() -> new UserFriendlyException(ErrorCode.UserNotFound));
        if(!passwordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            throw new UserFriendlyException(ErrorCode.PasswordIsIncorrect);
        }
        AuthResponseDto response = jwtUtil.generateToken(appUser);
        return new ApiResponse(response);
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody AuthRequestDto request) {
        if (userRepo.findFirstByUsername(request.getUsername()).isPresent()) {
            throw new UserFriendlyException(ErrorCode.UserAlreadyExist);
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        return new ApiResponse();
    }

    @PostMapping("/logout")
    public ApiResponse logout() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return null;

        String authHeader = attributes.getRequest().getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.substring(7);
            var tokenId = jwtUtil.getTokenId(token);
            tokenManagerService.revokeToken(tokenId);
            return new ApiResponse();
        }
        else {
            throw new UserFriendlyException(ErrorCode.TokenIsNull);
        }
    }

    @PostMapping("refresh-token")
    public ApiResponse refreshToken(String refreshToken) {
        var tokenId = jwtUtil.getTokenId(refreshToken);
        var userId = jwtUtil.getUserId(refreshToken);
        var username = jwtUtil.getUsername(refreshToken);
        long now = System.currentTimeMillis();
        var refreshTokenInfo = tokenManagerRepository.findByTokenIdAndTokenType(tokenId, TokenTypeConst.REFRESH_TOKEN)
                .orElseThrow(() -> new UserFriendlyException(ErrorCode.ListTokenIsEmpty));
        if(refreshTokenInfo.isRevoked() || refreshTokenInfo.getExpiredAt() < now) {
            throw new UserFriendlyException(ErrorCode.RefreshTokenIsExpired);
        }
        var appUser = new AppUser();
        appUser.setId(userId);
        appUser.setUsername(username);
        var tokenResponse = jwtUtil.generateToken(appUser);
        var result = new AuthResponseDto(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), tokenResponse.getExpiredDate());
        return new ApiResponse(result);
    }
}

