package com.fast.learners.platform.profiles.domain.model.commands;

import com.fast.learners.platform.profiles.domain.model.valueobjects.Membership;

public record CreateUserCommand(String firstName, String middleName, String lastName, String email, String street, String number, String city, String postalCode, String country, Membership membership) {

}
