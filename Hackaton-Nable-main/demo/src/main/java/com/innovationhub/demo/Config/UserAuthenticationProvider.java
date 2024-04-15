package com.innovationhub.demo.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.innovationhub.demo.Dto.CredentialsDto;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Repository.CustomerRepository;
import com.innovationhub.demo.Service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider {
    @Value("${secretKey}")
    private String secretKey;
    private final AuthenticationService authenticationService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 60 * 1000 * 60 * 24);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        Customer customer = (Customer) authenticationService.findByEmail(decoded.getIssuer());
        return new UsernamePasswordAuthenticationToken(customer, null, Collections.emptyList());
    }

    public Authentication validateCredentials(CredentialsDto credentialsDto) {

        Customer customer = authenticationService.authenticate(credentialsDto);
        return new UsernamePasswordAuthenticationToken(credentialsDto, null, Collections.emptyList());
    }
}
