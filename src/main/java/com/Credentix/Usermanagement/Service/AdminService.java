package com.Credentix.Usermanagement.Service;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AdminService {

    @Autowired
    private  UserRepository repository;

    public  List<User> findAllUser(){
         return repository.findAll();

    }





}
