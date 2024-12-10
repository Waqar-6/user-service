package com.w_farooq_group.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
public class UserProfile {
    @Id
    private UUID userId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    private String bio;

    public UserProfile (UUID userId, User user, String bio) {
        this.userId = userId;
        this.user = user;
        this.bio = bio;
    }
    public UserProfile(UUID userId, User user) {
        this.userId = userId;
        this.user = user;
    }
    public UserProfile() {}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
