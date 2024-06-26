package com.fast.learners.platform.profiles.domain.model.commands;


public record CreateProfileCommand(String firstName, String middleName, String lastName, String email, String password, String  membership) {

}
