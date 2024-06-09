package com.fast.learners.platform.users.application.internal.queryservices;
import com.fast.learners.platform.users.domain.model.aggregates.User;
import com.fast.learners.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.fast.learners.platform.users.domain.model.queries.GetAllUsersQuery;
import com.fast.learners.platform.users.domain.model.queries.GetUserByIdQuery;
import com.fast.learners.platform.users.domain.services.UserQueryService;
import com.fast.learners.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.emailAddress());
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.profileId());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}
