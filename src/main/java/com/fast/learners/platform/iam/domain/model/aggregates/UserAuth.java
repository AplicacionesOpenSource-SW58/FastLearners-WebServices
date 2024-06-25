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

@Getter
@Setter
@Entity
public class UserAuth extends AuditableAbstractAggregateRoot<UserAuth> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_membership",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "membership"))
    private Membership membership;

    public UserAuth() {

    }

    public UserAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuth(String username, String password, Membership membership) {
        this(username, password);
        addMembership(membership);
    }


    /**
     * Add a membership to the user
     * @param membership the membership to add
     * @return the user with the added role
     */
    public UserAuth addMembership(Membership membership) {
        this.membership = membership;
        return this;
    }



}
