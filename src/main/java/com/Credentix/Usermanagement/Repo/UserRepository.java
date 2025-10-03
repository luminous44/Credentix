package com.Credentix.Usermanagement.Repo;

import com.Credentix.Usermanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public boolean existsByEmail (String email);
    public User findByEmail(String email);
    public User findByEmailAndPhone(String email, String phone);


}
