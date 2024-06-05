package com.fast.learners.platform.profiles.interfaces.rest.resources;

public record CreateUserResource(String firstName, String middleName, String lastName, String email, String membership) {
}
