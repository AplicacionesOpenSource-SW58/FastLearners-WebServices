package com.fast.learners.platform.iam.infrastructure.authorization.sfs.services;

import com.fast.learners.platform.iam.infrastructure.authorization.sfs.model.UserAuthDetailsImpl;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.UserAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
@Service(value = "defaultUserAuthDetailsService")
public class UserAuthDetailsServiceImpl implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    public UserAuthDetailsServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    /**
     * This method is responsible for loading the user details from the database.
     * @param username The username.
     * @return The UserDetails object.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserAuthDetailsImpl.build(user);
    }
}
