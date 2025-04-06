package rest_api_app.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rest_api_app.Entity.User.AppUser;
import rest_api_app.Services.User.Abstracts.ITokenManagerService;
import rest_api_app.Services.User.Dtos.AuthDto.AuthResponseDto;
import rest_api_app.Services.User.Dtos.TokenDto.TokenDto;
import rest_api_app.Shared.Constant.TokenTypeConst;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    @Value("${jwt.config.secret-key}")
    private String SECRET_KEY;

    @Autowired
    private ITokenManagerService tokenManagerService;

    public AuthResponseDto generateToken(AppUser userDetails) {
        long now = System.currentTimeMillis();

        // 1. Access Token
        String accessTokenId = UUID.randomUUID().toString();
        long accessTokenExpiration = now + 1000 * 60 * 60; // 1 giờ

        String accessToken = generateJwtToken(userDetails, accessTokenId, accessTokenExpiration);
        TokenDto accessTokenInfo = buildTokenDto(accessTokenId, userDetails, TokenTypeConst.ACCESS_TOKEN, now, accessTokenExpiration);
        tokenManagerService.saveToken(accessTokenInfo);

        // 2. Refresh Token
        String refreshTokenId = UUID.randomUUID().toString();
        long refreshTokenExpiration = now + 1000L * 60 * 60 * 72; // 72 giờ

        String refreshToken = generateJwtToken(userDetails, refreshTokenId, refreshTokenExpiration);
        TokenDto refreshTokenInfo = buildTokenDto(refreshTokenId, userDetails, TokenTypeConst.REFRESH_TOKEN, now, refreshTokenExpiration);
        tokenManagerService.saveToken(refreshTokenInfo);

        return new AuthResponseDto(accessToken, refreshToken, accessTokenExpiration);
    }

    private String generateJwtToken(AppUser userDetails, String tokenId, Long expirationTime) {
        Claims claims = Jwts.claims();
        claims.put("user_id", userDetails.getId());
        claims.put("token_id", tokenId);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationTime))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    private TokenDto buildTokenDto(String tokenId, AppUser userDetails, String tokenType, long issuedAt, long expiredAt) {
        TokenDto dto = new TokenDto();
        dto.setTokenId(tokenId);
        dto.setUsername(userDetails.getUsername());
        dto.setTokenType(tokenType);
        dto.setIssuedDate(issuedAt);
        dto.setExpiredDate(expiredAt);
        return dto;
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenId(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("token_id", String.class);
    }

    public Long getUserId(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("user_id", Long.class);
    }

    public boolean validateToken(String token, AppUser userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
