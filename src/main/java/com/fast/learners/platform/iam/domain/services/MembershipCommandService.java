package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.SeedMembershipsCommand;

public interface MembershipCommandService {
    void handle(SeedMembershipsCommand command);
}