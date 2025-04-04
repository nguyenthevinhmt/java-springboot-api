package rest_api_app.Models.User;

import rest_api_app.Shared.EntityBase.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "USER_TYPE", nullable = false)
    private int userType;

    @Column(name = "DESCRIPTION", nullable = true, length = 255)
    private String description;

    @Column(name = "STATUS", nullable = false)
    private int status;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    // Constructor without 'id' field
    public Role(String name, int userType, String description, int status) {
        this.name = name;
        this.userType = userType;
        this.description = description;
        this.status = status;
    }
}
