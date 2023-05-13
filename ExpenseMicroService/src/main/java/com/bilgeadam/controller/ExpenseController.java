package com.bilgeadam.controller;

import com.bilgeadam.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+EXPENSE)
public class ExpenseController {


    private final ExpenseService expenseService;
}
