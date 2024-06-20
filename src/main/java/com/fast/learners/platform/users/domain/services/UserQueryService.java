package com.fast.learners.platform.users.domain.model.services;
import com.fast.learners.platform.users.domain.model.aggregates.User;
import com.fast.learners.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.fast.learners.platform.users.domain.model.queries.GetUserByIdQuery;
import com.fast.learners.platform.users.domain.model.queries.GetAllUsersQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserQueryService {
    Optional<User> handle(GetUserByEmailQuery query);

    Optional<User> handle(GetUserByIdQuery query);

    List<User> handle(GetAllUsersQuery query);
}
