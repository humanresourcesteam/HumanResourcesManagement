package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddCompanyRequestDto;
import com.bilgeadam.dto.response.GetAllInfoCompany;
import com.bilgeadam.dto.response.SummaryInfoCompany;
import com.bilgeadam.exception.CompanyException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.ICompanyMapper;
import com.bilgeadam.rabbitmq.model.CompanyName;
import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class CompanyService extends ServiceManager<Company, String> {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
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
    public boolean addCompany(AddCompanyRequestDto addCompanyRequestDto) throws IOException {
        Optional<Company> companyOptional = companyRepository.findOptionalByCentralRegistrySystemOrName(addCompanyRequestDto.getCentralRegistrySystem(), addCompanyRequestDto.getName());
        if (companyOptional.isPresent()) throw new CompanyException(EErrorType.COMPANY_HAS_BEEN);
        else {

            if (addCompanyRequestDto.getImage() == null) {
                Company company = Company.builder()
                        .centralRegistrySystem(addCompanyRequestDto.getCentralRegistrySystem())
                        .name(addCompanyRequestDto.getName())
                        .contractEndYear(addCompanyRequestDto.getContractEndYear())
                        .phone(addCompanyRequestDto.getPhone())
                        .image("https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg")
                        .address(addCompanyRequestDto.getAddress())
                        .taxNumber(addCompanyRequestDto.getTaxNumber())
                        .taxOffice(addCompanyRequestDto.getTaxOffice())
                        .contractStartYear(addCompanyRequestDto.getContractStartYear())
                        .numberOfWorkers(addCompanyRequestDto.getNumberOfWorkers())
                        .status(addCompanyRequestDto.getStatus())
                        .title(addCompanyRequestDto.getTitle())
                        .email(addCompanyRequestDto.getEmail())
                        .yearOfEstablishment(addCompanyRequestDto.getYearOfEstablishment())
                        .build();
                save(company);
            } else {
                Company company = Company.builder()
                        .centralRegistrySystem(addCompanyRequestDto.getCentralRegistrySystem())
                        .name(addCompanyRequestDto.getName())
                        .contractEndYear(addCompanyRequestDto.getContractEndYear())
                        .phone(addCompanyRequestDto.getPhone())
                        .address(addCompanyRequestDto.getAddress())
                        .taxNumber(addCompanyRequestDto.getTaxNumber())
                        .taxOffice(addCompanyRequestDto.getTaxOffice())
                        .contractStartYear(addCompanyRequestDto.getContractStartYear())
                        .numberOfWorkers(addCompanyRequestDto.getNumberOfWorkers())
                        .status(addCompanyRequestDto.getStatus())
                        .title(addCompanyRequestDto.getTitle())
                        .email(addCompanyRequestDto.getEmail())
                        .yearOfEstablishment(addCompanyRequestDto.getYearOfEstablishment())
                        .build();
                save(company);
                CompletableFuture.runAsync(() -> {
                    companyRepository.findOptionalByName(addCompanyRequestDto.getName())
                            .ifPresent(updatedCompany -> {
                                updatedCompany.setImage(imageUpload(addCompanyRequestDto.getImage()));
                                update(updatedCompany);
                            });
                }).exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                }).join();

            }
            return true;
        }
    }
    public List<SummaryInfoCompany> getAllCompanySummaryInfo() {
        List<SummaryInfoCompany> summaryInfoCompanies = new ArrayList<>();
        companyRepository.findAll().forEach(x -> {
            summaryInfoCompanies.add(SummaryInfoCompany.builder()
                    .id(x.getId())
                    .address(x.getAddress())
                    .title(x.getTitle())
                    .phone(x.getPhone())
                    .name(x.getName())
                    .email(x.getEmail())
                    .build());
        });
        return summaryInfoCompanies;
    }
    public GetAllInfoCompany getAllInfo(String id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        long diff = ChronoUnit.DAYS.between(companyOptional.get().getContractStartYear(), companyOptional.get().getContractEndYear());
        System.out.println(diff);
        LocalDate date = LocalDate.now();
        long remaining = ChronoUnit.DAYS.between(date, companyOptional.get().getContractEndYear());
        GetAllInfoCompany getAllInfoCompany = ICompanyMapper.INSTANCE.fromInfoCompany(companyOptional.get());
        getAllInfoCompany.setAllContractDay((int) diff);
        getAllInfoCompany.setRemainingDays((int) remaining);
        return getAllInfoCompany;
    }
    public String workerCompanyName(WorkerModel workerModel) {
        Optional<Company> company = companyRepository.findById(workerModel.getId());
        return company.get().getName();
    }
    public String companyIdForManager(CompanyName companyName) {
        Optional<Company> company = companyRepository.findOptionalByName(companyName.getCompanyName());
        return company.get().getId();

    }
    public String getCompanyName(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        return company.get().getName();
    }


}
