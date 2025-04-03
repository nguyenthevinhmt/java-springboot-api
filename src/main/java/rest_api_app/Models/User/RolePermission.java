package rest_api_app.Models.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE_PERMISSION")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)
    private Role role;

    @Column(name = "KEY_PERMISSION", nullable = false, length = 100)
    private String keyPermission;

    // Constructor không có ID để tiện tạo dữ liệu mới
    public RolePermission(Role role, String keyPermission) {
        this.role = role;
        this.keyPermission = keyPermission;
    }
}
