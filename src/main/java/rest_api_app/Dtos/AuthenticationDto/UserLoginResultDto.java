package rest_api_app.Dtos.AuthenticationDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResultDto {
    private String access_token;
    private String refresh_token;
}
