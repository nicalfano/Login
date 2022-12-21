package co.develhope.Login.auth.controller;

import co.develhope.Login.auth.entities.SignUpActivationDTO;
import co.develhope.Login.auth.entities.SignUpDTO;
import co.develhope.Login.auth.servicies.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpDTO signUpDTO)throws Exception{
        signUpService.signUp(signUpDTO);
    }
    @PostMapping("/signup/activation")
    public void activation(@RequestBody SignUpActivationDTO signUpActivationDTO) throws Exception {
        signUpService.activate(signUpActivationDTO);
    }
}
