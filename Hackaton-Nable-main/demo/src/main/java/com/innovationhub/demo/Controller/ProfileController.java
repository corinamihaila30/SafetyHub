package com.innovationhub.demo.Controller;

import com.innovationhub.demo.Model.Customer;
import com.innovationhub.demo.Model.Profile;
import com.innovationhub.demo.Service.ProfileService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileService profileService;
    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @GetMapping("")
    public Profile getProfile() {
        Customer customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Profile profile = profileService.getProfile(customer);
        System.out.println("profile_page");
        return profile;
    }
    @PostMapping("")
    public ResponseEntity<String> setProfile() {
        System.out.println("hello");
        return ResponseEntity.ok("hey");
    }
}
