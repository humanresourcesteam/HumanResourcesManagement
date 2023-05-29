package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddManagerRequestDto;
import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.response.GetAllInfoManager;
import com.bilgeadam.dto.response.SumamryInfoManager;
import com.bilgeadam.dto.response.SummarForCompany;
import com.bilgeadam.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.bilgeadam.constant.EndPoints.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.bilgeadam.constant.EndPoints.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+MANAGER)
@CrossOrigin("*")
public class ManagerController {

    private final ManagerService managerService;


    @PostMapping(value = "/add" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> addNewManager(AddManagerRequestDto addManagerRequestDto)  {
        return ResponseEntity.ok(managerService.addNewManager(addManagerRequestDto));
    }


    @GetMapping("/get-all-summary")
    public ResponseEntity<List<SumamryInfoManager>> getAllManagerSummarryInfo(){
        return ResponseEntity.ok(managerService.getAllManagerSummaryInfo());
        }

    @GetMapping("/manager/{id}")
    public ResponseEntity<GetAllInfoManager> getAllInfoManager(@PathVariable String id){
        return ResponseEntity.ok(managerService.getAllInfo(id));
    }


    @GetMapping("/find-five-manager")
    public ResponseEntity<?> getTop5Manager(){
        return ResponseEntity.ok(managerService.getTop5Manager());
    }


    @GetMapping("/get-image")
    public ResponseEntity<String> getImageForManager(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(managerService.getImageForManager(baseRequestDto));
    }
    @GetMapping("/manager-info")
    public ResponseEntity<GetAllInfoManager> getInfoForManager(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(managerService.getInfoForManager(baseRequestDto));
    }

    @GetMapping("/manager-company-info/{companyId}")
    public ResponseEntity<SummarForCompany> getSummaryForCompany(@PathVariable String companyId){
        return ResponseEntity.ok(managerService.summaryForCompany(companyId));
    }


    }

