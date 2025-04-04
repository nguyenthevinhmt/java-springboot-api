package rest_api_app.Repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import rest_api_app.Models.User.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);
}
