package rest_api_app.Entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TOKEN_MANAGER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TOKEN_ID", length = 50)
    private String tokenId;
    @Column(name = "REFER_TOKEN_ID", nullable = true, length = 50)
    private String referTokenId;
    @Column(name = "USERNAME", length = 50)
    private String username;
    @Column(name = "TOKEN_TYPE", length = 50)
    private String tokenType;
    @Column(name = "ISSUED_AT")
    private Long issuedAt;
    @Column(name = "EXPIRED_AT")
    private Long expiredAt;
    @Column(name = "DEVICE_INFO")
    private String deviceInfo;
    @Column(name = "REVOKED")
    private boolean revoked;

}
