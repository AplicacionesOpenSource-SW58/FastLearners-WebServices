package com.fast.learners.platform.users.interfaces.rest.resources;


public record UserResource(Long id,
                           String fullName,
                           String email,
                           String membership) {
}
