package com.innovationhub.demo.Service;

import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import com.innovationhub.demo.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    public Profile getProfile(Customer customer) {
        Profile profile = profileRepository.findByCustomer(customer);
        System.out.println(profile);
        return profile;
    }
}
