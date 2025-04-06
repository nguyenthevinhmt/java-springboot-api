package rest_api_app.Services.User.Abstracts;

import rest_api_app.Services.User.Dtos.AuthDto.AuthResponseDto;
import rest_api_app.Services.User.Dtos.TokenDto.TokenDto;

public interface ITokenManagerService {
    void saveToken(TokenDto input);
    void revokeToken(String tokenId);
}
