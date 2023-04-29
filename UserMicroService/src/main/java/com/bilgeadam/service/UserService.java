package com.bilgeadam.service;


import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceManager<User,String > {

    private final IUserRepository repository;

    public UserService(IUserRepository repository){
        super(repository);
        this.repository=repository;
    }
}
