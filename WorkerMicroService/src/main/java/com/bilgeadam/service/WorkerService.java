package com.bilgeadam.service;


import com.bilgeadam.dto.request.AddWorkerRequestDto;
import com.bilgeadam.dto.response.GetAllWorker;
import com.bilgeadam.dto.response.NewEmployeeSummary;
import com.bilgeadam.dto.response.SummaryWorker;
import com.bilgeadam.dto.response.WorkerListDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.WorkerException;
import com.bilgeadam.mapper.IWorkerMapper;
import com.bilgeadam.rabbitmq.model.CreateWorker;
import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.rabbitmq.producer.WorkerProducer;
import com.bilgeadam.repository.IWorkerRepository;
import com.bilgeadam.repository.entity.Worker;
import com.bilgeadam.repository.enums.Activity;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class WorkerService extends ServiceManager<Worker, String> {

    private final IWorkerRepository workerRepository;

    private final WorkerProducer workerProducer;

    private final JwtTokenManager jwtTokenManager;

    public WorkerService(IWorkerRepository workerRepository, WorkerProducer workerProducer, JwtTokenManager jwtTokenManager) {
        super(workerRepository);
        this.workerRepository = workerRepository;
        this.workerProducer = workerProducer;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Boolean addWorker(AddWorkerRequestDto workerRequestDto) {
        Long result = workerProducer.createAuth(CreateWorker.builder()
                .email(workerRequestDto.getEmail()).build());
        if (result != 0L) {
            Optional<Worker> workerOptional = workerRepository.findOptionalByIdentificationNumber(workerRequestDto.getIdentificationNumber());
            if (workerOptional.isPresent()) throw new WorkerException(EErrorType.WORKER_HAS_BEEN);
            if (workerRequestDto.getImage() == null) {
                Worker worker = Worker.builder()
                        .managerid(workerRequestDto.getManagerid())
                        .companyid(workerRequestDto.getCompanyid())
                        .image("https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg")
                        .name(workerRequestDto.getName())
                        .secondname(workerRequestDto.getSecondname())
                        .surname(workerRequestDto.getSurname())
                        .secondSurname(workerRequestDto.getSecondSurname())
                        .birthDate(workerRequestDto.getBirthDate())
                        .birthPlace(workerRequestDto.getBirthPlace())
                        .identificationNumber(workerRequestDto.getIdentificationNumber())
                        .dateOfEmployment(workerRequestDto.getDateOfEmployment())
                        .activity(Activity.WORKING)
                        .occupation(workerRequestDto.getOccupation())
                        .email(workerRequestDto.getEmail())
                        .authid(result)
                        .address(workerRequestDto.getAddress())
                        .companyPhone(workerRequestDto.getCompanyPhone())
                        .salary(workerRequestDto.getSalary())
                        .build();
                save(worker);
            } else {
                Worker worker = Worker.builder()
                        .managerid(workerRequestDto.getManagerid())
                        .companyid(workerRequestDto.getCompanyid())
                        .image(imageUpload(workerRequestDto.getImage()))
                        .name(workerRequestDto.getName())
                        .secondname(workerRequestDto.getSecondname())
                        .surname(workerRequestDto.getSurname())
                        .secondSurname(workerRequestDto.getSecondSurname())
                        .birthDate(workerRequestDto.getBirthDate())
                        .birthPlace(workerRequestDto.getBirthPlace())
                        .identificationNumber(workerRequestDto.getIdentificationNumber())
                        .dateOfEmployment(workerRequestDto.getDateOfEmployment())
                        .activity(Activity.WORKING)
                        .occupation(workerRequestDto.getOccupation())
                        .email(workerRequestDto.getEmail())
                        .authid(result)
                        .address(workerRequestDto.getAddress())
                        .salary(workerRequestDto.getSalary())
                        .companyPhone(workerRequestDto.getCompanyPhone())
                        .build();
                save(worker);
            }
            return true;
        } else throw new WorkerException(EErrorType.WORKER_HAS_BEEN);
    }


    public GetAllWorker getAllWorker(String token) {
        Optional<Long> auth = jwtTokenManager.getIdFromToken(token);
        if (auth.isEmpty()) throw new WorkerException(EErrorType.INVALID_TOKEN);
        Optional<Worker> workerOptional = workerRepository.findOptionalByAuthid(auth.get());
        return IWorkerMapper.INSTANCE.fromInfoWorker(workerOptional.get());
    }
    public GetAllWorker getAllWorkerForManager(String id) {
        Optional<Worker> workerOptional = findById(id);
        return IWorkerMapper.INSTANCE.fromInfoWorker(workerOptional.get());
    }

    public List<WorkerListDto> workerList(String id) {
        List<WorkerListDto> workerList = new ArrayList<>();
        String companyName = workerProducer.getNameWorkerFromCompany(WorkerModel.builder()
                .id(workerRepository.findOptionalByCompanyid(id).get().getCompanyid())
                .build());
        workerRepository.findAll().forEach(x -> {
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


    public String imageUpload(MultipartFile file) {
        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "doa04qdhh");
        config.put("api_key", "261194321947226");
        config.put("api_secret", "K5_9m33MSDBvu4MZuHhHWeFxNeA");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url + " --------------------------");
            return url;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<SummaryWorker> getAllWorkerForCompany(String companyid) {
        List<SummaryWorker> summaryWorkers = new ArrayList<>();
        workerRepository.findByCompanyid(companyid).forEach(x -> {
            summaryWorkers.add(SummaryWorker.builder()
                    .surname(x.getSurname())
                    .activity(x.getActivity())
                    .companyPhone(x.getCompanyPhone())
                    .email(x.getEmail())
                    .name(x.getName())
                    .id(x.getId())
                    .image(x.getImage())
                    .build());

        });

        return summaryWorkers;
    }

    public List<NewEmployeeSummary> newEmployeeSummary() {

        List<NewEmployeeSummary> newEmployeeSummaries = new ArrayList<>();
        workerRepository.findTop5ByOrderByCreatedateDesc().forEach(x -> {
            newEmployeeSummaries.add(NewEmployeeSummary.builder()
                    .image(x.getImage())
                    .name(x.getName())
                    .occupation(x.getOccupation())
                    .surname(x.getSurname())
                    .build());
        });
        return newEmployeeSummaries;
    }



}
