package co.develhope.Login.auth.entities;

import lombok.Data;

@Data
public class SignUpDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
