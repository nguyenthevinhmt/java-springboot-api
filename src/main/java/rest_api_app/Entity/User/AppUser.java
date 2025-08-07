package rest_api_app.Entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
