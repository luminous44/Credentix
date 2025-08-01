package com.Credentix.Usermanagement.Service;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User createUser(User user){
       return repo.save(user);
    }
    public boolean isExistUser(String email){

        return  repo.existsByEmail(email);
    }
}
