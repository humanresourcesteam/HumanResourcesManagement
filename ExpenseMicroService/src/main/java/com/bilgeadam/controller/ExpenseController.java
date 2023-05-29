package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.WorkerExpense;
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
@CrossOrigin("*")
public class ExpenseController {


    private final ExpenseService expenseService;
    @PostMapping("/expenses")
    public ResponseEntity<?>createExpense(CreateExpenseRequestDto createExpenseRequestDto){
        return ResponseEntity.ok(expenseService.createExpense(createExpenseRequestDto));
    }
    @GetMapping("/workers/{workerid}")
    public ResponseEntity<List<WorkerExpense>> getWorkerExpense(@PathVariable String workerid ){
        return ResponseEntity.ok(expenseService.getWorkerExpense(workerid));
    }
    @GetMapping("/managers/{managerid}")
        public ResponseEntity<List<WorkerExpenseForManager>> getWorkerExpenseForWorker(@PathVariable String managerid){
          return ResponseEntity.ok(expenseService.getExpenseForManager(managerid));
        }
    @PutMapping("/update")
    public ResponseEntity<?> updateExpense(@RequestBody UpdateStatusRequestDto updateStatusRequestDto){
        return ResponseEntity.ok(expenseService.updateExpense(updateStatusRequestDto));
    }

}
