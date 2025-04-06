package rest_api_app.Repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_api_app.Entity.User.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findFirstByUsername(String username);
}
