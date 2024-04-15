package com.innovationhub.demo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="profile")
public class Profile {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="profile_id")
    Integer profileId;
    @Column(name="full_name")
    String fullName;
    @Column(name="trust_score")
    Integer trustScore;
    @Column(name="university")
    String university;
    @Column(name="profile_picture")
    String profilePicture;
    @Column(name="city")
    String city;
    @Column(name="verified")
    Boolean verified;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Customer customer;
}
