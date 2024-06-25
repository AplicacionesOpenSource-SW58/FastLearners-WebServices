package com.fast.learners.platform.iam.interfaces.rest.transform;
import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.UserAuthResource;
public class UserAuthResourceFromEntityAssembler {
    public static UserAuthResource toResourceFromEntity(UserAuth user) {
        var membership = user.getMembership().getName().name();
        return new UserAuthResource(user.getId(), user.getUsername(), membership);
    }

}
