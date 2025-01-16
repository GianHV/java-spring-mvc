package vn.hoidanit.laptopshop.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * This service class implements the UserDetailsService interface,
 * which is used by Spring Security to load user-specific data during authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    // UserService is injected to interact with the user data source
    private final UserService userService;

    /*
     * Constructor that initializes the UserService dependency.
     * @param userService the service that manages user-related operations.
     */
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /*
     * This method is called by Spring Security to load the user by their username (email).
     * It fetches the user from the database and checks if the user exists.
     * If the user is not found, it throws a UsernameNotFoundException.
     * 
     * @param username the username (email) used to authenticate the user.
     * @return a UserDetails object representing the authenticated user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user based on email
        vn.hoidanit.laptopshop.domain.User user = this.userService.getUserByEmail(username);
        
        // If the user does not exist, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        // Return a UserDetails object that Spring Security will use
        return new User(
                user.getEmail(), // username
                user.getPassword(), // password
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())) // roles
        );
    }
}
