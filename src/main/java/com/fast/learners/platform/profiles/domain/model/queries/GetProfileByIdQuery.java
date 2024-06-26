package com.fast.learners.platform.profiles.domain.model.queries;

public record GetProfileByIdQuery(long userId) {

    public Long profileId() {

        return 1234_5678_9012_3456L;
    }
}
