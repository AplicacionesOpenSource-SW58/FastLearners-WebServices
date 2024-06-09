package com.fast.learners.platform.users.domain.model.commands;


public record CreateUserCommand(String firstName, String middleName, String lastName, String email, String  membership) {

}
