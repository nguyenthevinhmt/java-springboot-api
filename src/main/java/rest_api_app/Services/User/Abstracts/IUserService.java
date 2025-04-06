package rest_api_app.Services.User.Abstracts;

import rest_api_app.Entity.User.AppUser;

public interface IUserService {
    public AppUser loadUserByUsername(String username);
}
