package com.bilgeadam.service;

import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ManagerService extends ServiceManager<Manager,String > {

    private final IManagerRepository managerRepository;

    public ManagerService(IManagerRepository managerRepository){
        super(managerRepository);
        this.managerRepository=managerRepository;
    }
}
