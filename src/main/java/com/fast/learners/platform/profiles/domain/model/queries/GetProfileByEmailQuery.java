package com.fast.learners.platform.profiles.domain.model.queries;
import com.fast.learners.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
