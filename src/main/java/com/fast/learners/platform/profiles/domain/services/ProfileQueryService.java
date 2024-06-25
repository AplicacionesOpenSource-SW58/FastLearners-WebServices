package com.fast.learners.platform.profiles.domain.services;
import com.fast.learners.platform.profiles.domain.model.aggregates.Profile;
import com.fast.learners.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.fast.learners.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.fast.learners.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}
