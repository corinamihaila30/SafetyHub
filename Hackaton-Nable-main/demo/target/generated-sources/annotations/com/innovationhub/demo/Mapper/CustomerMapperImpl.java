package com.innovationhub.demo.Mapper;

import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-28T18:06:33+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer signUpToCustomer(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.email( signUpDto.getEmail() );
        customer.password( signUpDto.getPassword() );

        return customer.build();
    }

    @Override
    public Profile signUpToProfile(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        Profile.ProfileBuilder profile = Profile.builder();

        profile.fullName( signUpDto.getFullName() );
        profile.city( signUpDto.getCity() );

        return profile.build();
    }
}
