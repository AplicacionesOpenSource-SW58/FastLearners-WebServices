package com.fast.learners.platform.profiles.domain.model.commands;

public record CreateUserCommand(String firstName, String middleName, String lastName, String email, String street, String number, String city, String postalCode, String country) {

}
