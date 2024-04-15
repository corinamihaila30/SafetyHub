package com.innovationhub.demo.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innovationhub.demo.Dto.CredentialsDto;
import com.innovationhub.demo.Model.Customer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter{

    private static final ObjectMapper Mapper = new ObjectMapper();

    private final UserAuthenticationProvider provider;

    public UsernamePasswordAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.provider = userAuthenticationProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getServletPath());
        if ("/api/auth/login".equals(request.getServletPath())
                && HttpMethod.POST.matches(request.getMethod())) {
            CredentialsDto credentialsDto = Mapper.readValue(request.getInputStream(), CredentialsDto.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        provider.validateCredentials(credentialsDto)
                );
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
