package com.innovationhub.demo.Service;

import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Exceptions.AppException;
import com.innovationhub.demo.Mapper.CustomerMapper;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public void addCustomer(Customer customer) {

        // do some validations
            customerRepository.save(customer);
    }
    public Customer getCustomer(Integer id) {
        Customer customer = (Customer) customerRepository.findById(id).get();
        // do some validations
        return (Customer) customerRepository.findById(id).get();
    }

}

