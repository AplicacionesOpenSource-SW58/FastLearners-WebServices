package com.fast.learners.platform.iam.domain.model.queries;

import com.fast.learners.platform.iam.domain.model.valueobjects.Memberships;

public record GetMembershipByNameQuery(Memberships name) {
}