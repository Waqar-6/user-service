package com.w_farooq_group.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String bio;
    private String profilePictureUrl;

    @OneToOne(mappedBy = "userProfile")
    private BaseUserEntity baseUserEntity;
}
