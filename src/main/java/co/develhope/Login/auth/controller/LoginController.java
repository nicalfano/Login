package co.develhope.Login.auth.controller;

import co.develhope.Login.auth.entities.LoginDTO;
import co.develhope.Login.auth.entities.LoginRTO;
import co.develhope.Login.auth.servicies.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        LoginRTO loginRTO = loginService.Login(loginDTO);
        if(loginRTO == null)throw new Exception("Cannot login");
        return loginRTO;

    }
}
