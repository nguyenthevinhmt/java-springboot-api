package rest_api_app.Services.AuthenticationModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rest_api_app.Repository.UserRepository.UserRepository;
import rest_api_app.Shared.Common.Exception.ErrorCode;
import rest_api_app.Shared.Common.Exception.UserFriendlyException;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("JAVA ngu"));
    }
}
