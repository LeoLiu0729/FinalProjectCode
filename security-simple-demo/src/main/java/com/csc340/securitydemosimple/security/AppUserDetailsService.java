package com.csc340.securitydemosimple.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author sunny
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userDetailsService().loadUserByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        
        UserDetails user
                = User.withDefaultPasswordEncoder()
                        .username("anakin")
                        .password("no-sand")
                        .roles("PADAWAN")
                        .build();

        UserDetails mod
                = User.withDefaultPasswordEncoder()
                        .username("obiwan")
                        .password("hello-there")
                        .roles("KNIGHT")
                        .build();

        UserDetails admin
                = User.withDefaultPasswordEncoder()
                        .username("quigon")
                        .password("bigger-fish")
                        .roles("MASTER")
                        .build();

        return new InMemoryUserDetailsManager(user, mod, admin);
    }
}
