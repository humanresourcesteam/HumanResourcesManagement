package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreatePermissionRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.WorkerPermissionForManager;
import com.bilgeadam.dto.response.WorkerPermissionForWorker;
import com.bilgeadam.repository.entity.Permission;
import com.bilgeadam.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(API+VERSION+PERMISSION)
@CrossOrigin("*")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/create-permission")
    public ResponseEntity<?>createPermission(@RequestBody CreatePermissionRequestDto createPermissionRequestDto){
        return ResponseEntity.ok(permissionService.createPermission(createPermissionRequestDto));
    }

//    @GetMapping("/manager-worker-permission/{managerid}")
//    public ResponseEntity <WorkerPermissionForManager> getWorkerPermissionForManager(@PathVariable String managerid){
//        return ResponseEntity.ok(permissionService.workerPermissionForManager(managerid));
//    }
//
//    @GetMapping("/approval-status/{managerid}")
//    public ResponseEntity<List<WorkerPermissionForManager>> sortByStatus(@PathVariable String managerid,List<WorkerPermissionForManager>workerPermissionForManagers){
//        return ResponseEntity.ok(permissionService.sortByStatus(managerid,workerPermissionForManagers));
//    }

    @GetMapping("/worker/{workerid}")
    public ResponseEntity<List<WorkerPermissionForWorker>> getPermissionsForWorker(@PathVariable String workerid){
        return ResponseEntity.ok(permissionService.getPermissionsForWorker(workerid));
    }

    @GetMapping("/manager/{managerid}")
    public ResponseEntity<List<WorkerPermissionForManager>> getPermissionsForManager(@PathVariable String managerid){
        return ResponseEntity.ok(permissionService.getPermissionForManager(managerid));
    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateStatus(@RequestBody UpdateStatusRequestDto updateStatusRequestDto){
        return ResponseEntity.ok(permissionService.updateStatus(updateStatusRequestDto));
    }




}
