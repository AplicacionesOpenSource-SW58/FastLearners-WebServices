package com.fast.learners.platform.iam.domain.model.entities;
import com.fast.learners.platform.iam.domain.model.valueobjects.Memberships;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;


/**
 * Membership entity
 * <p>
 *     This entity represents the role of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With

public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Memberships name;

    public Membership(Memberships name) {
        this.name = name;
    }

    /**
     * Get the name of the role as a string
     * @return the name of the role as a string
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Get the default role
     * @return the default role
     */
    public static Membership getDefaultMembership() {
        return new Membership(Memberships.BASIC);
    }

    /**
     * Get the role from its name
     * @param name the name of the role
     * @return the role
     */
    public static Membership toMembershipFromName(String name) {
        return new Membership(Memberships.valueOf(name.toUpperCase()));
    }

    /**
     *Validate the role set
     * <p>
     *     This method validates the role set and returns the default role if the set is empty.
            * </p>
            * @param memberships the role set
     * @return the role set
     */
    public static List<Membership> validateMembershipSet(List<Membership> memberships) {
        if (memberships == null || memberships.isEmpty()) {
            return List.of(getDefaultMembership());
        }
        return memberships;
    }


}
