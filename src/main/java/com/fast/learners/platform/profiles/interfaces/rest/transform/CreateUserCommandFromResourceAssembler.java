package com.fast.learners.platform.profiles.interfaces.rest.transform;

import com.fast.learners.platform.profiles.domain.model.commands.CreateUserCommand;
import com.fast.learners.platform.profiles.interfaces.rest.resources.CreateUserResource
;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.firstName(), resource.middleName(), resource.lastName(), resource.email(), resource.membership());
    }
}
