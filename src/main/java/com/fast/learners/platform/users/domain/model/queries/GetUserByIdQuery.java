package com.fast.learners.platform.users.domain.model.queries;

public record GetUserByIdQuery(long userId) {

    public Long profileId() {

        return 1234_5678_9012_3456L;
    }
}
