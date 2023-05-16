package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.response.WorkerAdvanceForManager;
import com.bilgeadam.service.AdvanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequestMapping(API+VERSION+ADVANCE)
@RequiredArgsConstructor
public class AdvanceController {

    private final AdvanceService advanceService;

    @PostMapping("/advances")
    public ResponseEntity<?> createAdvance(@RequestBody CreateAdvanceRequestDto createAdvanceRequestDto){
        return ResponseEntity.ok(advanceService.createAdvance(createAdvanceRequestDto));
    }

    @GetMapping("advances/{managerid}")
    public ResponseEntity<WorkerAdvanceForManager>getWorkerAdvanceForManager(@PathVariable String managerid){
        return ResponseEntity.ok(advanceService.workerAdvanceForManager(managerid));
    }

    @GetMapping("/approval-status/{managerid}")
    public ResponseEntity<List<WorkerAdvanceForManager>> sortByStatus(@PathVariable String managerid, List<WorkerAdvanceForManager>workerAdvanceForManagers){
        return ResponseEntity.ok(advanceService.sortByStatus(managerid,workerAdvanceForManagers));
    }

    @GetMapping("/sort-by-approval-status")
    public ResponseEntity<?>getApprovalStatus(){
        return ResponseEntity.ok(advanceService.getApprovalStatus());
    }

}
