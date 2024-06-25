package com.fast.learners.platform.iam.interfaces.acl;

import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByUsernameQuery;
import com.fast.learners.platform.iam.domain.services.UserAuthCommandService;
import com.fast.learners.platform.iam.domain.services.UserAuthQueryService;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
public class IamContextFacade {
    private final UserAuthCommandService userAuthCommandService;
    private final UserAuthQueryService userAuthQueryService;

    public IamContextFacade(UserAuthCommandService userAuthCommandService, UserAuthQueryService userAuthQueryService) {
        this.userAuthCommandService = userAuthCommandService;
        this.userAuthQueryService = userAuthQueryService;
    }

    /**
     * Creates a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password) {
        var signUpCommand = new SignUpCommand(username, password, (Membership.getDefaultMembership()));
        var result = userAuthCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates a user with the given username, password and memberships.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param membershipType The type of membership of the created user.
     */
    public Long createUser(String username, String password, String membershipType) {
        Membership membership = membershipType != null ? Membership.toMembershipFromName(membershipType) : new Membership();
        var signUpCommand = new SignUpCommand(username, password, membership);
        var result = userAuthCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserAuthByUsernameQuery(username);
        var result = userAuthQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserAuthByIdQuery(userId);
        var result = userAuthQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }



}
