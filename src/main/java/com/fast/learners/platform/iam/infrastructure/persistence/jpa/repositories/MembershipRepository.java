package com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories;

import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.valueobjects.Memberships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the Membership entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    /**
     * This method is responsible for finding the membership by name.
     * @param name The membership name.
     * @return The membership object.
     */
    Optional<Membership> findByName(Memberships name);

    /**
     * This method is responsible for checking if the membership exists by name.
     * @param name The membership name.
     * @return True if the membership exists, false otherwise.
     */
    boolean existsByName(Memberships name);

}