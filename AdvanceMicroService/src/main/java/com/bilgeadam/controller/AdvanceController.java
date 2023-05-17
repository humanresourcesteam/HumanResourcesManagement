package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.AdvanceForWorker;
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
@CrossOrigin("*")
public class AdvanceController {

    private final AdvanceService advanceService;

    @PostMapping("/advances")
    public ResponseEntity<?> createAdvance(@RequestBody CreateAdvanceRequestDto createAdvanceRequestDto){
        return ResponseEntity.ok(advanceService.createAdvance(createAdvanceRequestDto));
    }
    @GetMapping("/workers/{workerid}")
    public ResponseEntity<List<AdvanceForWorker>> getAdvanceForWorker(@PathVariable String workerid){
        return ResponseEntity.ok(advanceService.getAdvancesForWorker(workerid));
    }

    @GetMapping("/managers/{managerid}")
    public ResponseEntity<List<WorkerAdvanceForManager>> getAdvanceForManager(@PathVariable String managerid){
        return ResponseEntity.ok(advanceService.getAdvanceForManager(managerid));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusRequestDto updateStatusRequestDto){
        return ResponseEntity.ok(advanceService.updateStatus(updateStatusRequestDto));
    }


//    @GetMapping("advances/{managerid}")
//    public ResponseEntity<WorkerAdvanceForManager>getWorkerAdvanceForManager(@PathVariable String managerid){
//        return ResponseEntity.ok(advanceService.workerAdvanceForManager(managerid));
//    }
//
//    @GetMapping("/approval-status/{managerid}")
//    public ResponseEntity<List<WorkerAdvanceForManager>> sortByStatus(@PathVariable String managerid, List<WorkerAdvanceForManager>workerAdvanceForManagers){
//        return ResponseEntity.ok(advanceService.sortByStatus(managerid,workerAdvanceForManagers));
//    }
//
//    @GetMapping("/sort-by-approval-status")
//    public ResponseEntity<?>getApprovalStatus(){
//        return ResponseEntity.ok(advanceService.getApprovalStatus());
//    }

}
