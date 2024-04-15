package com.innovationhub.demo.Service;

import com.innovationhub.demo.Dto.CredentialsDto;
import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Exceptions.AppException;
import com.innovationhub.demo.Mapper.CustomerMapper;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import com.innovationhub.demo.Repository.CustomerRepository;
import com.innovationhub.demo.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProfileRepository profileRepository;

    public Customer authenticate(CredentialsDto credentialsDto) {
        Customer customer = customerRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("User Not Found", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), customer.getPassword())) {
            return customer;
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public Customer findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Login not found", HttpStatus.NOT_FOUND));
        return customer;
    }

    public void signUp(SignUpDto signupDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(signupDto.getEmail());

        if (optionalCustomer.isPresent()) {
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }
        if (signupDto.getPassword().length() <= 8) {
            throw new AppException("Password needs to be larger", HttpStatus.NOT_ACCEPTABLE);
        }
        if (signupDto.getPassword().length() >= 40) {
            throw new AppException("Password needs to be smaller", HttpStatus.NOT_ACCEPTABLE);
        }
        Customer customer = customerMapper.signUpToCustomer(signupDto);
        Profile profile = customerMapper.signUpToProfile(signupDto);
        System.out.println(profile);
        customer.setPassword(passwordEncoder.encode(CharBuffer.wrap(signupDto.getPassword())));
        profile.setTrustScore(10);
        profile.setVerified(false);
        profile.setCustomer(customer);
        customerRepository.save(customer);
        profileRepository.save(profile);
    }

}
