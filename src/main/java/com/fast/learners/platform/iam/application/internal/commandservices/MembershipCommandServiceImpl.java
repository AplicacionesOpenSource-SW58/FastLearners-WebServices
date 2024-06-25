package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.commands.SeedMembershipsCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.valueobjects.Memberships;
import com.fast.learners.platform.iam.domain.services.MembershipCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of {@link MembershipCommandService} to handle {@link SeedMembershipsCommand}
 */
@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * This method will handle the {@link SeedMembershipsCommand} and will create the memberships if not exists
     * @param command {@link SeedMembershipsCommand}
     * @see SeedMembershipsCommand
     */
    @Override
    public void handle(SeedMembershipsCommand command) {
        Arrays.stream(Memberships.values()).forEach(membership -> {
            if(!membershipRepository.existsByName(membership)) {
                membershipRepository.save(new Membership(Memberships.valueOf(membership.name())));
            }
        } );
    }
}