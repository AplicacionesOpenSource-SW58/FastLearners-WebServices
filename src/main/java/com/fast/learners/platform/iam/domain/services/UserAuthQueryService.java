package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fast.learners.platform.iam.domain.model.queries.GetAllUsersAuthQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByUsernameQuery;

import java.util.List;
import java.util.Optional;
public interface UserAuthQueryService {
    List<UserAuth> handle(GetAllUsersAuthQuery query);
    Optional<UserAuth> handle(GetUserAuthByIdQuery query);
    Optional<UserAuth> handle(GetUserAuthByUsernameQuery query);

}
