package com.fast.learners.platform.users.interfaces.rest.resources;

public record CreateUserResource(String firstName,
                                 String middleName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String membership) {
}
