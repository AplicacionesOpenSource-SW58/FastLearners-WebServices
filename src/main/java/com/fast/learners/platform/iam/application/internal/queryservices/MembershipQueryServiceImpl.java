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
 * This class is used to handle the role queries
 */
@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {
    private final MembershipRepository membershipRepository;

    /**
     * RoleQueryServiceImpl constructor
     * @param membershipRepository the role repository
     */
    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * Handle the get all membership query
     * @param query the get all memberships query
     * @return List<Role> the list of memberships
     */
    @Override
    public List<Membership> handle(GetAllMembershipsQuery query) {
        return membershipRepository.findAll();
    }

    /**
     * Handle the get role by name query
     * @param query the get role by name query
     * @return Optional<Role> the role
     */
    @Override
    public Optional<Membership> handle(GetMembershipByNameQuery query) {
        return membershipRepository.findByName(query.name());
    }
}
