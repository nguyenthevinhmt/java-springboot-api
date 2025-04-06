package rest_api_app.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @Column(name= "PASSWORD", nullable = false, length = 512)
    private String password;
    @Column(name = "PHONE", nullable = true, length = 50)
    private String phone;
    @Column(name = "ADDRESS", nullable = true, length = 150)
    private String address;

    public AppUser(String username, String password, String phone, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
