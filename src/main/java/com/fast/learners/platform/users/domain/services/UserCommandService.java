package com.fast.learners.platform.users.domain.services;
import com.fast.learners.platform.users.domain.model.aggregates.User;
import com.fast.learners.platform.users.domain.model.commands.CreateUserCommand;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
}
