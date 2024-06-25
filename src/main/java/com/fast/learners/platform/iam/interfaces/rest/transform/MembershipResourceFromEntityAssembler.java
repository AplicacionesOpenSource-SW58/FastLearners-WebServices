package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.MembershipResource;

public class MembershipResourceFromEntityAssembler {
    public static MembershipResource toResourceFromEntity(Membership membership) {
        return new MembershipResource(membership.getId(), membership.getStringName());
    }
}