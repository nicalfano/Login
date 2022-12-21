package co.develhope.Login.auth.entities;

import lombok.Data;

@Data
public class RestorePasswordDTO {

    private String newPassword;
    private String resetPasswordCode;
}
