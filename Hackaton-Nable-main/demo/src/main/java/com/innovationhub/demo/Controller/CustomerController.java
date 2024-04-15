package com.innovationhub.demo.Controller;

import com.innovationhub.demo.Config.UserAuthenticationProvider;
import com.innovationhub.demo.Dto.CredentialsDto;
import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Mapper.CustomerMapper;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Service.CustomerService;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;
     private UserAuthenticationProvider provider;
     private CustomerMapper customerMapper;
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper,  UserAuthenticationProvider provider) {
        this.customerService = customerService;
        this.provider = provider;
        this.customerMapper = customerMapper;
    }
    @GetMapping("")
    public String getPage() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hello";
    }
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }
    @PostMapping("")
    public void addCustomer(@RequestBody SignUpDto signUpDto) {
        Customer customer = customerMapper.signUpToCustomer(signUpDto);
        System.out.println(customer);
        customerService.addCustomer(customer);
    }



}
