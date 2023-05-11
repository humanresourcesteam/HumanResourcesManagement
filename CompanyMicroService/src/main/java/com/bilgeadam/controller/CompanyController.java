package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddCompanyRequestDto;
import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.response.GetAllInfoCompany;
import com.bilgeadam.dto.response.SummaryInfoCompany;
import com.bilgeadam.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+COMPANY)
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(ADDCOMPANY)
    public ResponseEntity<Boolean> addCompany(AddCompanyRequestDto addCompanyRequestDto) throws IOException {
        return ResponseEntity.ok(companyService.addCompany(addCompanyRequestDto));
    }
    @GetMapping("/get-all-summary")
    public ResponseEntity<List<SummaryInfoCompany>>getAllCompanySummaryInfo(){
        return ResponseEntity.ok(companyService.getAllCompanySummaryInfo());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<GetAllInfoCompany>getAllInfoCompany(@PathVariable String id){
        return ResponseEntity.ok(companyService.getAllInfo(id));
    }

    @GetMapping("/company-info-for-admin/{companyId}")
    public  ResponseEntity<String> getCompanyNameForManager(@PathVariable String companyId){
        return ResponseEntity.ok(companyService.getCompanyName(companyId));
    }

}
