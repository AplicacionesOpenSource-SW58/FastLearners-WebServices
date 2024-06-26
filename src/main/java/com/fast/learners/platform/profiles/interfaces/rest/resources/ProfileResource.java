package com.fast.learners.platform.profiles.interfaces.rest.resources;


public record ProfileResource(Long id,
                              String fullName,
                              String email,
                              String membership) {
}
