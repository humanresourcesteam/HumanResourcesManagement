package com.bilgeadam.service;

import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.request.UpdateAdminInfoRequestDto;
import com.bilgeadam.dto.response.DetailResponseDto;
import com.bilgeadam.dto.response.SummaryResponseDto;
import com.bilgeadam.exception.AdminException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.rabbitmq.model.CreateModel;
import com.bilgeadam.rabbitmq.model.UpdateAuthModel;
import com.bilgeadam.rabbitmq.producer.UpdateAuthProducer;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.entity.Admin;

import com.bilgeadam.utility.FileService;

import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class AdminService extends ServiceManager<Admin, String> {

    private final IAdminRepository repository;

    private final JwtTokenManager jwtTokenManager;

    private final UpdateAuthProducer updateAuthProducer;
    private final FileService fileService;

    public AdminService(IAdminRepository repository, JwtTokenManager jwtTokenManager, UpdateAuthProducer updateAuthProducer, FileService fileService) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.fileService = fileService;
        this.updateAuthProducer = updateAuthProducer;

    }

    public List<SummaryResponseDto> getSummary(BaseRequestDto baseRequestDto) {
        Optional<Long> auth = jwtTokenManager.getIdFromToken(baseRequestDto.getToken());
        if (auth.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        List<SummaryResponseDto> summaryList = new ArrayList<>();
        findAll().forEach(x -> {
            summaryList.add(SummaryResponseDto.builder()
                    .email(x.getEmail())
                    .surname(x.getSurname())
                    .firstName(x.getFirstName())
                    .build());
        });
        return summaryList;
    }


    public List<DetailResponseDto> getDetailInformation(BaseRequestDto baseRequestDto) {
        Optional<Long> auth = jwtTokenManager.getIdFromToken(baseRequestDto.getToken());
        if (auth.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        List<DetailResponseDto> detailInformation = new ArrayList<>();
        findAll().forEach(x -> {
            detailInformation.add(DetailResponseDto.builder()
                    .surname(x.getSurname())
                    .email(x.getEmail())
                    .dateOfEmployment(x.getDateOfEmployment())
                    .firstName(x.getFirstName())
                    .build());
        });
        return detailInformation;
    }

    public DetailResponseDto getDetailInformationForAdmin(BaseRequestDto baseRequestDto) {
        Optional<Long> auth = jwtTokenManager.getIdFromToken(baseRequestDto.getToken());
        if (auth.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        Optional<Admin> admin = repository.findOptionalByAuthid(auth.get());
        return IAdminMapper.INSTANCE.toDetailResponseDto(admin.get());
    }

    public String imageUpload(MultipartFile file, Long id) {
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

    public boolean updateInfo(UpdateAdminInfoRequestDto updateRequestDto) throws IOException {
        Optional<Long> authid = jwtTokenManager.getIdFromToken(updateRequestDto.getToken());
        if (authid.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        Optional<Admin> admin = repository.findOptionalByAuthid(authid.get());
        if (admin.get().getEmail().equals(updateRequestDto.getEmail())) {
            if (updateRequestDto.getImage() != null) {
                admin.get().setImage(imageUpload(updateRequestDto.getImage(), authid.get()));
            }
            admin.get().setEmail(updateRequestDto.getEmail());
            admin.get().setSurname(updateRequestDto.getSurname());
        //    admin.get().setDateOfEmployment(updateRequestDto.getDateOfEmployment());
            admin.get().setAuthid(admin.get().getAuthid());
            admin.get().setFirstName(updateRequestDto.getFirstName());
            update(admin.get());
        } else {
            boolean result = updateAuthProducer.updateAuth(UpdateAuthModel.builder()
                    .email(updateRequestDto.getEmail())
                    .authid(authid.get())
                    .build());
            if (result == true) {
                if (updateRequestDto.getImage() != null) {

                    admin.get().setImage(imageUpload(updateRequestDto.getImage(), authid.get()));
               }
                admin.get().setEmail(updateRequestDto.getEmail());
                admin.get().setSurname(updateRequestDto.getSurname());
        //        admin.get().setDateOfEmployment(updateRequestDto.getDateOfEmployment());
                admin.get().setAuthid(admin.get().getAuthid());
                admin.get().setFirstName(updateRequestDto.getFirstName());
                update(admin.get());
                return true;
            }
        }
        return false;
    }


    public void saveAdmin(CreateModel createModel) {
        Admin admin = Admin.builder()
                .authid(createModel.getAuthid())
                .email(createModel.getEmail())
                .dateOfEmployment(LocalDate.now())
                .build();
        save(admin);
    }


}
