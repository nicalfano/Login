package co.develhope.Login.auth.entities;

import co.develhope.Login.user.entities.User;
import lombok.Data;

@Data
public class LoginRTO {
private User user;
private String JWT;
}
