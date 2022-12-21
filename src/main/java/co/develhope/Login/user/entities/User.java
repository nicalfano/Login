package co.develhope.Login.user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    private String password;
    private boolean isActvive;
    private LocalDateTime jwtCreatedOn;

    @Column(length = 36)
    private String activationCode;
    private String passwordResetCode;
}
