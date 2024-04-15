package com.innovationhub.demo.Mapper;

import com.innovationhub.demo.Dto.SignUpDto;
import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source="email", target = "email")
    @Mapping(source="password", target = "password")
    Customer signUpToCustomer(SignUpDto signUpDto);

    @Mapping(source="fullName", target="fullName")
    @Mapping(source="city", target="city")
    Profile signUpToProfile(SignUpDto signUpDto);

}
