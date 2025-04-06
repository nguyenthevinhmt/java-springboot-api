package rest_api_app.Services.User.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Entity.User.AppUser;
import rest_api_app.Repository.User.UserRepository;
import rest_api_app.Services.User.Abstracts.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public AppUser loadUserByUsername(String username) {
        AppUser user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserFriendlyException(ErrorCode.UserNotFound));

        return new AppUser(user.getUsername(), user.getPassword(), user.getPhone(), user.getAddress());
    }
}