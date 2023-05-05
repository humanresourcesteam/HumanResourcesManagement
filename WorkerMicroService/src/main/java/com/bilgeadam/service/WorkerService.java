package com.bilgeadam.service;


import com.bilgeadam.repository.IWorkerRepository;
import com.bilgeadam.repository.entity.Worker;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class WorkerService extends ServiceManager<Worker,String > {

    private final IWorkerRepository repository;

    public WorkerService(IWorkerRepository repository){
        super(repository);
        this.repository=repository;
    }
}
