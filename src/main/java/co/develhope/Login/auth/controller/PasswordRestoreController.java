package co.develhope.Login.auth.controller;

import co.develhope.Login.auth.entities.RequestPasswordDTO;
import co.develhope.Login.auth.entities.RestorePasswordDTO;
import co.develhope.Login.auth.servicies.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordRestoreController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/request")
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception {
    try{
        passwordService.request(requestPasswordDTO);
    }catch (Exception e){

    }
    }
    @PostMapping("/restore")
    public void passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception {
    passwordService.restore(restorePasswordDTO);
    }
}
