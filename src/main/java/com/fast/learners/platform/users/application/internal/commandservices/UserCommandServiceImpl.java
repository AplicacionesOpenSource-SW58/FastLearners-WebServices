package com.fast.learners.platform.users.application.internal.commandservices;
import com.fast.learners.platform.users.domain.model.commands.CreateUserCommand;
import com.fast.learners.platform.users.domain.model.aggregates.User;
import com.fast.learners.platform.users.domain.model.valueobjects.EmailAddress;
import com.fast.learners.platform.users.domain.services.UserCommandService;
import com.fast.learners.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* @Override */
    public Optional<User> handle(CreateUserCommand command) {
        var emailAddress = new EmailAddress(command.email());
        userRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        });
        var profile = new User(command);
        userRepository.save(profile);
        return Optional.of(profile);
    }
}