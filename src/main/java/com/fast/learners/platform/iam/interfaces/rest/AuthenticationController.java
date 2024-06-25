package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.services.UserAuthCommandService;
import com.fast.learners.platform.iam.interfaces.rest.resources.AuthenticatedUserAuthResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.SignInResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.SignUpResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.UserAuthResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.AuthenticatedUserAuthResourceFromEntityAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserAuthCommandService userCommandService;

    public AuthenticationController(UserAuthCommandService userAuthCommandService) {
        this.userCommandService = userAuthCommandService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserAuthResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserAuthResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }
    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserAuthResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserAuthResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
