package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddWorkerRequestDto;
import com.bilgeadam.dto.response.GetAllWorker;
import com.bilgeadam.dto.response.NewEmployeeSummary;
import com.bilgeadam.dto.response.SummaryWorker;
import com.bilgeadam.dto.response.WorkerListDto;
import com.bilgeadam.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+WORKERS)
@CrossOrigin("*")
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping("/addworker")
    public ResponseEntity<Boolean>addWorker(AddWorkerRequestDto workerRequestDto){
        return ResponseEntity.ok(workerService.addWorker(workerRequestDto));
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<GetAllWorker>getAllWorker(@PathVariable String id){
        return ResponseEntity.ok(workerService.getAllWorker(id));
    }

    @GetMapping("/listworker/{id}")
    public ResponseEntity<List<WorkerListDto>>workerList(@PathVariable String id){
        return ResponseEntity.ok(workerService.workerList(id));
    }

    @GetMapping("/get-all-employee-for-company/{companyid}")
    public ResponseEntity<List<SummaryWorker>> getAllWorkerForCompany(@PathVariable String companyid){
        return ResponseEntity.ok(workerService.getAllWorkerForCompany(companyid));
    }

    @GetMapping("/get-new-employee")
    public ResponseEntity<List<NewEmployeeSummary>> getNewEmployee(){
        return ResponseEntity.ok(workerService.newEmployeeSummary());
    }



}
