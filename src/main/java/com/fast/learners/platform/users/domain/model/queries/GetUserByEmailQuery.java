package com.fast.learners.platform.users.domain.model.queries;
import com.fast.learners.platform.users.domain.model.valueobjects.EmailAddress;

public record GetUserByEmailQuery(EmailAddress emailAddress) {
}
