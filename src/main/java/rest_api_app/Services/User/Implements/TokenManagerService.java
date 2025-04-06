package rest_api_app.Services.User.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Entity.User.TokenManager;
import rest_api_app.Repository.User.TokenManagerRepository;
import rest_api_app.Services.User.Abstracts.ITokenManagerService;
import rest_api_app.Services.User.Dtos.TokenDto.TokenDto;

@Service
public class TokenManagerService implements ITokenManagerService {
    @Autowired
    private TokenManagerRepository tokenManagerRepository;

    @Override
    public void saveToken(TokenDto input) {
        var tokenInfo = new TokenManager();
        tokenInfo.setTokenId(input.getTokenId());
        tokenInfo.setTokenType(input.getTokenType());
        tokenInfo.setRevoked(false);
        tokenInfo.setExpiredAt(input.getExpiredDate());
        tokenInfo.setIssuedAt(input.getIssuedDate());
        tokenInfo.setUsername(input.getUsername());
        tokenInfo.setDeviceInfo(input.getDeviceInfo());

        tokenManagerRepository.save(tokenInfo);
    }

    @Override
    public void revokeToken(String tokenId) {
        var tokenEntity = tokenManagerRepository.findByTokenId(tokenId);
        if(tokenEntity.isEmpty()) {
            throw new UserFriendlyException(ErrorCode.ListTokenIsEmpty);
        }
        tokenEntity.forEach(tokenManager -> {
            tokenManager.setRevoked(true);
        });

        tokenManagerRepository.saveAll(tokenEntity);
    }
}
