package com.bilgeadam.service;

import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.request.UpdateAdminInfoRequestDto;
import com.bilgeadam.dto.response.DetailResponseDto;
import com.bilgeadam.dto.response.SummaryResponseDto;
import com.bilgeadam.exception.AdminException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin, String> {

    private final IAdminRepository repository;

    private final JwtTokenManager jwtTokenManager;

    public AdminService(IAdminRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public List<SummaryResponseDto> getSummary(BaseRequestDto baseRequestDto) {
        Optional<Long> auth = jwtTokenManager.getIdFromToken(baseRequestDto.getToken());
        if (auth.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        List<SummaryResponseDto> summaryList = new ArrayList<>();
        findAll().forEach(x -> {
            summaryList.add(SummaryResponseDto.builder()
                    .address(x.getAddress())
                    .email(x.getEmail())
                    .phone(x.getPhone())
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
                    .address(x.getAddress())
                    .phone(x.getPhone())
                    .surname(x.getSurname())
                    .email(x.getEmail())
                    .birthdate(x.getBirthdate())
                    .dateOfEmployment(x.getDateOfEmployment())
                    .secondSurname(x.getSecondSurname())
                    .placeOfBirth(x.getPlaceOfBirth())
                    .identificationNumber(x.getIdentificationNumber())
                    .secondName(x.getSecondName())
                    .firstName(x.getFirstName())
                    .build());
        });
        return detailInformation;
    }

    public String  updateInfo(UpdateAdminInfoRequestDto updateRequestDto) {
        Optional<Long> authid = jwtTokenManager.getIdFromToken(updateRequestDto.getToken());
        if (authid.isEmpty()) throw new AdminException(EErrorType.INVALID_TOKEN);
        Optional<Admin> admin = repository.findOptionalByAuthid(authid.get());
        System.out.println(admin);
        admin.get().setAddress(updateRequestDto.getAddress());
        admin.get().setBirthdate(updateRequestDto.getBirthdate());
        admin.get().setEmail(updateRequestDto.getEmail());
        admin.get().setPhone(updateRequestDto.getPhone());
        admin.get().setSurname(updateRequestDto.getSurname());
        admin.get().setDateOfEmployment(updateRequestDto.getDateOfEmployment());
        admin.get().setAuthid(authid.get());
        admin.get().setPlaceOfBirth(updateRequestDto.getPlaceOfBirth());
        admin.get().setSecondSurname(updateRequestDto.getSecondSurname());
        admin.get().setFirstName(updateRequestDto.getFirstName());
        admin.get().setSecondName(updateRequestDto.getSecondName());
        update(admin.get());
        return "bilgiler güncellendi";
    }
}
