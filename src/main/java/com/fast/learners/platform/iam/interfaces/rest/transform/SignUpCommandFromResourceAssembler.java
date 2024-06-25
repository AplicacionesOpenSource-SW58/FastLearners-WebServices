package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.*;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var membership = resource.membership() != null ? Membership.toMembershipFromName(resource.membership()) : new Membership();
        return new SignUpCommand(resource.username(), resource.password(), membership);
    }
}
