package com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.valueobjects.Memberships;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/**
 * This interface is responsible for providing the Role entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long>{

    /**
     * This method is responsible for finding the role by name.
     * @param name The role name.
     * @return The role object.
     */
    Optional<Membership> findByName(Memberships name);

    /**
     * This method is responsible for checking if the role exists by name.
     * @param name The role name.
     * @return True if the role exists, false otherwise.
     */
    boolean existsByName(Memberships name);

}
