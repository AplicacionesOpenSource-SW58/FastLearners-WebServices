package com.fast.learners.platform.iam.application.internal.queryservices;

import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.queries.GetAllMembershipsQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetMembershipByNameQuery;
import com.fast.learners.platform.iam.domain.services.MembershipQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * RoleQueryServiceImpl class
 * This class is used to handle the membership queries
 */
@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {
    private final MembershipRepository membershipRepository;

    /**
     * RoleQueryServiceImpl constructor
     * @param membershipRepository the membership repository
     */
    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * Handle the get all memberships query
     * @param query the get all memberships query
     * @return List<Membership> the list of memberships
     */
    @Override
    public List<Membership> handle(GetAllMembershipsQuery query) {
        return membershipRepository.findAll();
    }

    /**
     * Handle the get membership by name query
     * @param query the get membership by name query
     * @return Optional<Membership> the membership
     */
    @Override
    public Optional<Membership> handle(GetMembershipByNameQuery query) {
        return membershipRepository.findByName(query.name());
    }
}