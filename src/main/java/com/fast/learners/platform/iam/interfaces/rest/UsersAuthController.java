package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.queries.GetAllUsersAuthQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserAuthByIdQuery;
import com.fast.learners.platform.iam.domain.services.UserAuthQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.UserAuthResource;
import com.fast.learners.platform.iam.domain.services.UserAuthQueryService;
import com.fast.learners.platform.iam.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/

@RestController
@RequestMapping(value = "/api/v1/users2", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersAuthController {

    private final UserAuthQueryService userAuthQueryService;
    public UsersAuthController(UserAuthQueryService userQueryService) {
        this.userAuthQueryService = userQueryService;
    }
    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserAuthResource
     */
    @GetMapping
    public ResponseEntity<List<UserAuthResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersAuthQuery();
        var users = userAuthQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserAuthResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserAuthResource
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserAuthResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserAuthByIdQuery(userId);
        var user = userAuthQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserAuthResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}
