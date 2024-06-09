package com.fast.learners.platform.users.interfaces.rest.transform;

import com.fast.learners.platform.users.domain.model.commands.CreateUserCommand;
import com.fast.learners.platform.users.interfaces.rest.resources.CreateUserResource
;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.firstName(), resource.middleName(), resource.lastName(), resource.email(), resource.membership());
    }
}
