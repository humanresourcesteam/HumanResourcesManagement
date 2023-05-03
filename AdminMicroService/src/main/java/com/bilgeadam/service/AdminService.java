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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin, String> {

    private final IAdminRepository repository;

    private final JwtTokenManager jwtTokenManager;

    private final FileService fileService;

    private final UpdateAuthProducer updateAuthProducer;

    public AdminService(IAdminRepository repository, JwtTokenManager jwtTokenManager, FileService fileService, UpdateAuthProducer updateAuthProducer) {
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

    public boolean   updateInfo(UpdateAdminInfoRequestDto updateRequestDto) throws IOException {
        Optional<Long> authid = jwtTokenManager.getIdFromToken(updateRequestDto.getToken());
        if (authid.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        Optional<Admin> admin = repository.findOptionalByAuthid(authid.get());
        boolean result =  updateAuthProducer.updateAuth(UpdateAuthModel.builder()
                .email(updateRequestDto.getEmail())
                .authid(authid.get())
                .build());
        if (result==true){
            if (updateRequestDto.getImage()!=""){
                String fileName =  fileService.decodeBase64(updateRequestDto.getImage());
                admin.get().setImage(fileName);
            }
            admin.get().setEmail(updateRequestDto.getEmail());
            admin.get().setSurname(updateRequestDto.getSurname());
            admin.get().setDateOfEmployment(updateRequestDto.getDateOfEmployment());
            admin.get().setAuthid(admin.get().getAuthid());

            admin.get().setFirstName(updateRequestDto.getFirstName());

            update(admin.get());
            System.out.println("ali");
            return true;
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
