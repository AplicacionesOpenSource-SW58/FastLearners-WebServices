package com.fast.learners.platform.users.interfaces.rest;

import com.fast.learners.platform.users.domain.model.queries.GetAllUsersQuery;
import com.fast.learners.platform.users.domain.model.queries.GetUserByIdQuery;
import com.fast.learners.platform.users.domain.services.UserCommandService;
import com.fast.learners.platform.users.domain.services.UserQueryService;
import com.fast.learners.platform.users.interfaces.rest.resources.CreateUserResource;
import com.fast.learners.platform.users.interfaces.rest.resources.UserResource;
import com.fast.learners.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.fast.learners.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UsersController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")

public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    /**
       * Creates a new Profile
     * @param resource the resource containing the data to create the Profile
     * @return the created Profile
     */

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(createUserCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Gets a Profile by its id
     * @param userId the id of the Profile to get
     * @return the Profile resource associated to given Profile id
     */

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getProfileByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getProfileByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(profileResource);
    }


    /**
     * Gets all the Users
     * @return a list of all the Users resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }


}
