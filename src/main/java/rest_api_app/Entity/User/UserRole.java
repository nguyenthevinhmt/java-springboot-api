package rest_api_app.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "ROLE_ID")
    private Long roleId;
}
