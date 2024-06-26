package com.fast.learners.platform.profiles.interfaces.rest;

import com.fast.learners.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.fast.learners.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.fast.learners.platform.profiles.domain.services.ProfileCommandService;
import com.fast.learners.platform.profiles.domain.services.ProfileQueryService;
import com.fast.learners.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.fast.learners.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.fast.learners.platform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.fast.learners.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UsersAuthController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "UserAuth Management Endpoints")

public class ProfilesController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    /**
       * Creates a new Profile
     * @param resource the resource containing the data to create the Profile
     * @return the created Profile
     */

    @PostMapping
    public ResponseEntity<ProfileResource> createUser(@RequestBody CreateProfileResource resource) {
        var createUserCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createUserCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Gets a Profile by its id
     * @param userId the id of the Profile to get
     * @return the Profile resource associated to given Profile id
     */

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResource> getUserById(@PathVariable Long userId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }


    /**
     * Gets all the Users
     * @return a list of all the Users resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllProfilesQuery();
        var users = profileQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }
}
