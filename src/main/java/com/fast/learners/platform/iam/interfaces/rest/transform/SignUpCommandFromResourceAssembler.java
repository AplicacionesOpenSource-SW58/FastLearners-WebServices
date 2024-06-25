package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.*;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var memberships = resource.memberships() != null ? resource.memberships().stream().map(name -> Membership.toMembershipFromName(name)).toList() : new ArrayList<Membership>();
        return new SignUpCommand(resource.username(), resource.password(), memberships);
    }
}