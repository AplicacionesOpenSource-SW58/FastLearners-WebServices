package com.fast.learners.platform.iam.domain.services;
import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fast.learners.platform.iam.domain.model.commands.SignInCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Optional;
public interface UserAuthCommandService {
    Optional<ImmutablePair<UserAuth, String>> handle(SignInCommand command);
    Optional<UserAuth> handle(SignUpCommand command);
}
