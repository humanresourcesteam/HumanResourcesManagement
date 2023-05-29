package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddWorkerRequestDto;
import com.bilgeadam.dto.request.UpdateWorkerRequestDto;
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
    @GetMapping("/worker/{token}")
    public ResponseEntity<GetAllWorker>getAllWorker(@PathVariable String token){
        return ResponseEntity.ok(workerService.getAllWorker(token));
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<GetAllWorker>getAllWorkerForManager(@PathVariable String id){
        return ResponseEntity.ok(workerService.getAllWorkerForManager(id));
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

    @PostMapping("/update")
    public ResponseEntity<?>updateWorkerProfile(UpdateWorkerRequestDto updateWorkerRequestDto){
        System.out.println("deene");
        return ResponseEntity.ok(workerService.updateWorker(updateWorkerRequestDto));
    }

    @GetMapping("/getallworker")
    public ResponseEntity<Long> getAllWorkerCount(){
        return ResponseEntity.ok(workerService.getAllWorkerCount());
    }

}
