package rest_api_app.Services.User.Dtos.TokenDto;

import lombok.Data;

import java.util.Date;

@Data
public class TokenDto {
    private String tokenId;
    private String username;
    private String tokenType;
    private Long issuedDate;
    private Long expiredDate;
    private String deviceInfo;
}
