package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.queries.GetAllMembershipsQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetMembershipByNameQuery;

import java.util.List;
import java.util.Optional;

public interface MembershipQueryService {
    List<Membership> handle(GetAllMembershipsQuery query);
    Optional<Membership> handle(GetMembershipByNameQuery query);
}