package rest_api_app.Repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_api_app.Entity.User.TokenManager;

import java.util.List;
import java.util.Optional;

public interface TokenManagerRepository extends JpaRepository<TokenManager, Long> {
    public List<TokenManager> findByTokenId(String tokenId);
    Optional<TokenManager> findByTokenIdAndTokenType(String tokenId, String tokenType);
}
