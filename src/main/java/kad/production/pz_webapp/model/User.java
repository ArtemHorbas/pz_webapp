package kad.production.pz_webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime registrationDate;

    public User(String firstName, String secondName, String email, String password, Role role) {
        this.id = 0;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.registrationDate = LocalDateTime.now();
    }
}
