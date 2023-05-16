package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.response.WorkerExpenseForManager;
import com.bilgeadam.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+EXPENSE)
public class ExpenseController {


    private final ExpenseService expenseService;

    @PostMapping("/expenses")
    public ResponseEntity<?>createExpense(@RequestBody CreateExpenseRequestDto createExpenseRequestDto){
        return ResponseEntity.ok(expenseService.createExpense(createExpenseRequestDto));
    }

    @GetMapping("/manager-worker-expense/{managerid}")
    public ResponseEntity<WorkerExpenseForManager>getWorkerExpenseForManager(@PathVariable String managerid){
        return ResponseEntity.ok(expenseService.workerExpenseForManager(managerid));
    }

    @GetMapping("/approval-status/{managerid}")
    public ResponseEntity<List<WorkerExpenseForManager>>sortByStatus(@PathVariable String managerid,List<WorkerExpenseForManager>workerExpenseForManagers){
        return ResponseEntity.ok(expenseService.sortByStatus(managerid,workerExpenseForManagers));
    }

    @GetMapping("sort-by-approval-status")
    public ResponseEntity<?>getApprovalStatus(){
        return ResponseEntity.ok(expenseService.getApprovalStatus());
    }

    @GetMapping("/sort-by-approval-status-approved")
    public ResponseEntity<?>getApprovalStatusApproved(){
        return ResponseEntity.ok(expenseService.getApprovalStatusApproved());
    }
}
