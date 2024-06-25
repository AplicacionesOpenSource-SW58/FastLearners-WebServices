package com.fast.learners.platform.iam.interfaces.rest.transform;
import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fast.learners.platform.iam.interfaces.rest.resources.AuthenticatedUserAuthResource;

public class AuthenticatedUserAuthResourceFromEntityAssembler {

    public static AuthenticatedUserAuthResource toResourceFromEntity(UserAuth user, String token) {
        return new AuthenticatedUserAuthResource(user.getId(), user.getUsername(), token);
    }
}
