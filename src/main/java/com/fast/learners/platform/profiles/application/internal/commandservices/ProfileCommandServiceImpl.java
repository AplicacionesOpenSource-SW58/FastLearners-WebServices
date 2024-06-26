package com.fast.learners.platform.profiles.application.internal.commandservices;
import com.fast.learners.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.fast.learners.platform.profiles.domain.model.aggregates.Profile;
import com.fast.learners.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.fast.learners.platform.profiles.domain.services.ProfileCommandService;
import com.fast.learners.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /* @Override */
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("UserAuth with email " + command.email() + " already exists");
        });
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}