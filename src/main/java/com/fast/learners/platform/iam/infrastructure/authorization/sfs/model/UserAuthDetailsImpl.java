package com.fast.learners.platform.iam.infrastructure.authorization.sfs.model;

import com.fast.learners.platform.iam.domain.model.aggregates.UserAuth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetails interface.
 */
@Getter
@EqualsAndHashCode
public class UserAuthDetailsImpl implements UserDetails{

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserAuthDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * This method is responsible for building the UserAuthDetailsImpl object from the User object.
     * @param user The user object.
     * @return The UserAuthDetailsImpl object.
     */
    public static UserAuthDetailsImpl build(UserAuth user) {
        String membership = user.getMembership().getName().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(membership);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(authority); // Create a list of authorities containing the single authority

        return new UserAuthDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}