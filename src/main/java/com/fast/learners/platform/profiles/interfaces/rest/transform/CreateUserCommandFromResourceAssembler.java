package com.fast.learners.platform.profiles.interfaces.rest.transform;

import com.fast.learners.platform.profiles.domain.model.commands.CreateUserCommand;
import com.fast.learners.platform.profiles.interfaces.rest.resources.;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateUserCommand(resource.firstName(), resource.lastName(), resource.email(), resource.street(), resource.number(), resource.city(), resource.postalCode(), resource.country());
    }
}
