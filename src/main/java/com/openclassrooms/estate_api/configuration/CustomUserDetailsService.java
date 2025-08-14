package com.openclassrooms.estate_api.configuration;

import com.openclassrooms.estate_api.repository.DBUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final DBUserRepository dbUserRepository;

    public CustomUserDetailsService(DBUserRepository dbUserRepository) {
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = dbUserRepository.findByUsername(username);

        return new User(dbUser.getUsername(), dbUser.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}