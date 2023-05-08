package com.bilgeadam.service;


import com.bilgeadam.dto.request.AddWorkerRequestDto;
import com.bilgeadam.dto.response.GetAllWorker;
import com.bilgeadam.dto.response.WorkerListDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.WorkerException;
import com.bilgeadam.mapper.IWorkerMapper;
import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.rabbitmq.producer.WorkerProducer;
import com.bilgeadam.repository.IWorkerRepository;
import com.bilgeadam.repository.entity.Worker;
import com.bilgeadam.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService extends ServiceManager<Worker,String > {

    private final IWorkerRepository repository;

    private final WorkerProducer workerProducer;

    public WorkerService(IWorkerRepository repository, WorkerProducer workerProducer){
        super(repository);
        this.repository=repository;
        this.workerProducer = workerProducer;
    }

    public Boolean addWorker(AddWorkerRequestDto workerRequestDto) {
        Optional<Worker>workerOptional = repository.findOptionalByIdentificationNumber(workerRequestDto.getIdentificationNumber());
        if (workerOptional.isPresent()) throw new WorkerException(EErrorType.WORKER_HAS_BEEN);

        // isterlere göre şekillenecek...
        return true;
    }


    public GetAllWorker getAllWorker(String id) {
        Optional<Worker>workerOptional = repository.findById(id);
        return IWorkerMapper.INSTANCE.fromInfoWorker(workerOptional.get());
    }


    public List<WorkerListDto> workerList(String id) {
        List<WorkerListDto>workerList = new ArrayList<>();
        // manager id den bir liste olustur
        //id olustugunda
        String companyName = workerProducer.getNameWorkerFromManager(WorkerModel.builder()
                        .id(repository.findOptionalByManagerid(id).get().getCompanyid())
                .build());
        repository.findAll().forEach(x->{
            workerList.add(WorkerListDto.builder()
                            .name(x.getName())
                            .email(x.getEmail())
                            .companyname(companyName)
                            .surname(x.getSurname())
                            .phone(x.getCompanyPhone())
                    .build());
        });
        return workerList;
    }
}
