package com.innovationhub.demo.Repository;

import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByCustomer(Customer customer);
}
