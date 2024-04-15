package com.innovationhub.demo.Controller;


import com.innovationhub.demo.Config.UserAuthenticationProvider;
import com.innovationhub.demo.Dto.CredentialsDto;
import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Mapper.CustomerMapper;
import com.innovationhub.demo.Service.AuthenticationService;
import com.innovationhub.demo.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private UserAuthenticationProvider provider;
    private CustomerService customerService;
    private AuthenticationService authenticationService;
    public AuthController(CustomerService customerService, UserAuthenticationProvider provider, AuthenticationService authenticationService) {
        this.provider = provider;
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<CredentialsDto> loginCustomer(CredentialsDto credentialsDto) {
        System.out.println(credentialsDto);
        credentialsDto = (CredentialsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        credentialsDto.setToken(provider.createToken(credentialsDto.getEmail()));
        return ResponseEntity.ok(credentialsDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpDto> signUpCustomer(@RequestBody SignUpDto signupDto) {
        System.out.println("Tried to sign up a customer");
        authenticationService.signUp(signupDto);
        return ResponseEntity.ok(signupDto);
    }
}
