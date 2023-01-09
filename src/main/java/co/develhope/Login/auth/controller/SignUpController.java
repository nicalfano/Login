package co.develhope.Login.auth.controller;

import co.develhope.Login.auth.entities.SignUpActivationDTO;
import co.develhope.Login.auth.entities.SignUpDTO;
import co.develhope.Login.auth.servicies.SignUpService;
import co.develhope.Login.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpDTO signUpDTO)throws Exception{
        return signUpService.signUp(signUpDTO);
    }
    @PostMapping("/signup/{role}")
    public User signUp(@RequestBody SignUpDTO signUpDTO, @PathVariable String role)throws Exception{
        return signUpService.signUp(signUpDTO, role);
    }
    @PostMapping("/signup/activation")
    public User activation(@RequestBody SignUpActivationDTO signUpActivationDTO) throws Exception {
        return signUpService.activate(signUpActivationDTO);
    }
}
