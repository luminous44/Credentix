package com.Credentix.Usermanagement.Service;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userD = repo.findByEmail(email);
        if(userD != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userD.getEmail())
                    .password(userD.getPassword())
                    .roles(userD.getRole())
                    .build();
            return  userDetails;
        }

        throw  new UsernameNotFoundException("User not found with username: "+email);
    }
}
