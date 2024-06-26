package com.fast.learners.platform.iam.domain.model.aggregates;

import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Profile aggregate root
 * This class represents the aggregate root for the Profile entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Membership> memberships;

    public User() {
        this.memberships = new HashSet<>();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.memberships = new HashSet<>();
    }

    public User(String username, String password, List<Membership> memberships) {
        this(username, password);
        addMemberships(memberships);
    }

    /**
     * Add a membership to the user
     * @param membership the membership to add
     * @return the user with the added membership
     */
    public User addRole(Membership membership) {
        this.memberships.add(membership);
        return this;
    }

    /**
     * Add a list of memberships to the user
     * @param memberships the list of memberships to add
     * @return the user with the added memberships
     */
    public User addMemberships(List<Membership> memberships) {
        var validatedRoleSet = Membership.validateMembershipSet(memberships);
        this.memberships.addAll(validatedRoleSet);
        return this;
    }

}