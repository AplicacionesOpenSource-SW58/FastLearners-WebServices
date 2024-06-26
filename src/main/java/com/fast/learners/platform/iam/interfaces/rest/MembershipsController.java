package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.queries.GetAllMembershipsQuery;
import com.fast.learners.platform.iam.domain.services.MembershipQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.MembershipResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  Memberships Controller
 *  This controller is responsible for handling all the requests related to memberships
 */
@RestController
@RequestMapping(value = "/ap/v1/memberships", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Memberships", description = "Membership Management Endpoints")
public class MembershipsController {
    private final MembershipQueryService membershipQueryService;

    public MembershipsController(MembershipQueryService membershipQueryService) {
        this.membershipQueryService = membershipQueryService;
    }

    /**
     * Get all memberships
     * @return List of membership resources
     * @see MembershipResource
     */
    @GetMapping
    public ResponseEntity<List<MembershipResource>> getAllMemberships() {
        var getAllMembershipsQuery = new GetAllMembershipsQuery();
        var memberships = membershipQueryService.handle(getAllMembershipsQuery);
        var membershipResources = memberships.stream().map(MembershipResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(membershipResources);
    }
}