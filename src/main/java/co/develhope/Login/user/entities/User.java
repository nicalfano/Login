package co.develhope.Login.user.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "USER_ROLES",
            joinColumns = {
                @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")
            })
    private Set<Role> roles;
}
