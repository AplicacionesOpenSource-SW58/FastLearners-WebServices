package com.fast.learners.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(String firstName,
                                    String middleName,
                                    String lastName,
                                    String email,
                                    String password,
                                    String membership) {
}
