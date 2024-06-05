package com.fast.learners.platform.profiles.interfaces.rest.transform;

import com.fast.learners.platform.profiles.domain.model.aggregates.User;
import com.fast.learners.platform.profiles.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getEmailAddress(), entity.getFullName(), entity.getMembership());
    }
}
