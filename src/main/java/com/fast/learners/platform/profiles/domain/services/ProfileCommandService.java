package com.fast.learners.platform.profiles.domain.services;
import com.fast.learners.platform.profiles.domain.model.aggregates.Profile;
import com.fast.learners.platform.profiles.domain.model.commands.CreateProfileCommand;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
