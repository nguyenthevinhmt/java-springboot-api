package rest_api_app.Models.User;

import Shared.Constant.CommonStatus;
import Shared.Constant.UserType;
import Shared.EntityBase.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "USER_NAME", nullable = false, unique = true, length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotNull
    @Size(min = 6)
    private String password;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    @NotNull
    @Email
    private String email;
    @Column(name = "ADDRESS", nullable = true, length = 255)
    private String address;;
    @Column(name = "PHONE", nullable = false, length = 15)
    @NotNull
    private String phone;
    @Column(name = "STATUS")
    @NotNull
    private int status;
    @Column(name = "USER_TYPE", nullable = false)
    @NotNull
    private int userType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public User(String userName, String password, String email, String address, String phone, int status, int userType) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.userType = userType;
    }

    public User(String userName, String password, String email, String address, String phone) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.status = CommonStatus.Active;
        this.userType = UserType.ADMIN;
    }
}
