package com.bilgeadam.service;

import com.bilgeadam.repository.IExpenseRepository;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService extends ServiceManager<Expense,String > {

    private final IExpenseRepository expenseRepository;

    public ExpenseService(IExpenseRepository expenseRepository) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
    }
}
