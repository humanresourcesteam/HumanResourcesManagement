package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreatePermissionRequestDto;
import com.bilgeadam.dto.response.WorkerPermissionForManager;
import com.bilgeadam.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(API+VERSION+PERMISSION)
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/create-permission")
    public ResponseEntity<?>createPermission(@RequestBody CreatePermissionRequestDto createPermissionRequestDto){
        return ResponseEntity.ok(permissionService.createPermission(createPermissionRequestDto));
    }

    @GetMapping("/manager-worker-permission/{managerid}")
    public ResponseEntity <WorkerPermissionForManager> getWorkerPermissionForManager(@PathVariable String managerid){
        return ResponseEntity.ok(permissionService.workerPermissionForManager(managerid));
    }

    @GetMapping("/approval-status/{managerid}")
    public ResponseEntity<List<WorkerPermissionForManager>> sortByStatus(@PathVariable String managerid,List<WorkerPermissionForManager>workerPermissionForManagers){
        return ResponseEntity.ok(permissionService.sortByStatus(managerid,workerPermissionForManagers));
    }

    @GetMapping("/sort-by-approval-status")
    public ResponseEntity<?>getApprovalStatus(){
        return ResponseEntity.ok(permissionService.getApprovalStatus());
    }

    @GetMapping("/sort-by-approval-status-approved")
    public ResponseEntity<?>getApprovalStatusApproved(){
        return ResponseEntity.ok(permissionService.getApprovalStatusApproved());
    }
}
