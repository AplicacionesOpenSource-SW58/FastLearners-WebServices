package com.fast.learners.platform.iam.application.internal.queryservices;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByIdQuery;
import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fast.learners.platform.iam.domain.model.queries.GetAllUsersAuthQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByUsernameQuery;
import com.fast.learners.platform.iam.domain.services.UserAuthQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserAuthQueryService} interface.
 */
@Service
public class UserAuthQueryServiceImpl implements UserAuthQueryService {

    private final UserAuthRepository userAuthRepository;

    /**
     * Constructor.
     *
     * @param userAuthRepository {@link UserAuthRepository} instance.
     */
    public UserAuthQueryServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    /**
     * This method is used to handle {@link GetAllUsersAuthQuery} query.
     * @param query {@link GetAllUsersAuthQuery} instance.
     * @return {@link List} of {@link UserAuth} instances.
     * @see GetAllUsersAuthQuery
     */
    @Override
    public List<UserAuth> handle(GetAllUsersAuthQuery query) {
        return userAuthRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetUserAuthByIdQuery} query.
     * @param query {@link GetUserAuthByIdQuery} instance.
     * @return {@link Optional} of {@link UserAuth} instance.
     * @see GetUserAuthByIdQuery
     */
    @Override
    public Optional<UserAuth> handle(GetUserAuthByIdQuery query) {
        return userAuthRepository.findById(query.userId());
    }

    /**
     * This method is used to handle {@link GetUserAuthByUsernameQuery} query.
     * @param query {@link GetUserAuthByUsernameQuery} instance.
     * @return {@link Optional} of {@link UserAuth} instance.
     * @see GetUserAuthByUsernameQuery
     */
    @Override
    public Optional<UserAuth> handle(GetUserAuthByUsernameQuery query) {
        return userAuthRepository.findByUsername(query.username());
    }

}
