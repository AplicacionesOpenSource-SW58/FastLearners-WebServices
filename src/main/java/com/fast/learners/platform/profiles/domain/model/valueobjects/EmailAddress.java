package com.fast.learners.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String email) {
    public EmailAddress() { this(null); }

    public String getEmail() {
        return String.format("%s", email) ;
    }
}
