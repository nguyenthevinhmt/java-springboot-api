package rest_api_app.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ROLE_PERMISSION")
public class RolePermisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "KEY_PERMISSION", nullable = false, length = 50)
    private String keyPermission;
    @Column(name = "PARENT_ID")
    private String parentId;
}
