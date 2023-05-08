package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddManagerRequestDto;
import com.bilgeadam.dto.response.GetAllInfoManager;
import com.bilgeadam.dto.response.SumamryInfoManager;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.ManagerException;
import com.bilgeadam.mapper.IManagerMapper;
import com.bilgeadam.rabbitmq.model.CreateManager;
import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.rabbitmq.producer.ManagerProducer;
import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.utility.FileService;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService extends ServiceManager<Manager, String> {

    private final IManagerRepository managerRepository;
    private final ManagerProducer managerProducer;
    private final FileService fileService;

    public ManagerService(IManagerRepository managerRepository, ManagerProducer managerProducer, FileService fileService) {
        super(managerRepository);
        this.managerRepository = managerRepository;
        this.managerProducer = managerProducer;
        this.fileService = fileService;
    }

    public boolean addNewManager(AddManagerRequestDto addManagerRequestDto) throws IOException {
        Optional<Manager> managerOptional = managerRepository.findOptionalByIdentificationNumber(addManagerRequestDto.getIdentificationNumber());
        if (managerOptional.isPresent()) throw new ManagerException(EErrorType.MANAGER_HAS_BEEN);
        Long result = managerProducer.createAuthFromManager(CreateManager.builder()
                .email(addManagerRequestDto.getEmail())
                .build());
        if (result == 0L) throw new ManagerException(EErrorType.AUTH_EMAIL_ERROR);
        else {
            String fileName = fileService.decodeBase64(addManagerRequestDto.getImage());
            Manager manager = Manager.builder()
                    .address(addManagerRequestDto.getAddress())
                    .email(addManagerRequestDto.getEmail())
                    .firstName(addManagerRequestDto.getFirstName())
                    .image(fileName)
                    .phone(addManagerRequestDto.getPhone())
                    .identificationNumber(addManagerRequestDto.getIdentificationNumber())
                    .dateOfEmployment(addManagerRequestDto.getDateOfEmployment())
                    .surname(addManagerRequestDto.getSurname())
                    .authid(result)
                    .birthdayPlace(addManagerRequestDto.getBirthdayPlace())
                    .birthDate(addManagerRequestDto.getBirthDate())
                    .build();
            save(manager);
            return true;
        }


    }

    public List<SumamryInfoManager> getAllManagerSummaryInfo() {
        List<SumamryInfoManager> sumamryInfoManagers = new ArrayList<>();
        managerRepository.findAll().forEach(x -> {
            sumamryInfoManagers.add(SumamryInfoManager.builder()
                    .address(x.getAddress())
                    .id(x.getId())
                    .surname(x.getSurname())
                    .firstName(x.getFirstName())
                    .image(x.getImage())
                    .email(x.getEmail())
                    .phone(x.getPhone())

                    .build());
        });
        return sumamryInfoManagers;
    }

    public GetAllInfoManager getAllInfo(String id) {
        Optional<Manager> managerOptional = managerRepository.findById(id);
        return IManagerMapper.INSTANCE.froInfoManager(managerOptional.get());

    }

    public List<SumamryInfoManager> getTop5Manager() {
        List<SumamryInfoManager> sumamryInfoManagers = new ArrayList<>();
        managerRepository.findTop5ByOrderByCreatedateDesc().forEach(x -> {
            sumamryInfoManagers.add(SumamryInfoManager.builder()
                            .id(x.getId())
                            .image(x.getImage())
                    .firstName(x.getFirstName())
                    .surname(x.getSurname())
                    .email(x.getEmail())
                    .build());
        });
        return sumamryInfoManagers;
    }

//    public String  workerCompanyName(WorkerModel workerModel) {
//        Optional<Manager> manager = managerRepository.findOptionalById(workerModel.getId());
//        manager.get().
//        return
//    }
}
