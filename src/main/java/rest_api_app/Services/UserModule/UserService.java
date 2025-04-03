package rest_api_app.Services.UserModule;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);
}
