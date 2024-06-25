package com.fast.learners.platform.profiles.interfaces.rest.transform;

import com.fast.learners.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.fast.learners.platform.profiles.interfaces.rest.resources.CreateProfileResource
;

public class CreateProfileCommandFromResourceAssembler {

    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.middleName(), resource.lastName(), resource.email(), resource.password(),resource.membership());
    }
}
