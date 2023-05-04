package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddManagerRequestDto;
import com.bilgeadam.dto.response.SumamryInfoManager;
import com.bilgeadam.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
@CrossOrigin("*")
public class ManagerController {

    private final ManagerService managerService;


    @PostMapping("/add")
    public ResponseEntity<?> addNewManager(@RequestBody AddManagerRequestDto addManagerRequestDto){
        return ResponseEntity.ok(managerService.addNewManager(addManagerRequestDto));
    }


    @GetMapping("/get-all-summary")
    public ResponseEntity<List<SumamryInfoManager>> getAllManagerSummarryInfo(){
        return ResponseEntity.ok(managerService.getAllManagerSummaryInfo());
        }
    }

