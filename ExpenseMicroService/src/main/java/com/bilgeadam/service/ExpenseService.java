package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.response.ApprovalStatusResponse;
import com.bilgeadam.dto.response.WorkerExpenseForManager;
import com.bilgeadam.repository.IExpenseRepository;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.bilgeadam.repository.enums.ApprovalStatus.APPROVED;
import static com.bilgeadam.repository.enums.ApprovalStatus.PENDING_APPROVAL;

@Service
public class ExpenseService extends ServiceManager<Expense,String > {

    private final IExpenseRepository expenseRepository;

    public ExpenseService(IExpenseRepository expenseRepository) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(CreateExpenseRequestDto createExpenseRequestDto) {
        Expense expense = Expense.builder()
                .expenditureType(createExpenseRequestDto.getExpenditureType())
                .managerid(createExpenseRequestDto.getManagerid())
                .workerid(createExpenseRequestDto.getWorkerid())
                .amountOfExpenditure(createExpenseRequestDto.getAmountOfExpenditure())
                .currency(createExpenseRequestDto.getCurrency())
                .replyDate(createExpenseRequestDto.getReplyDate())
                .file(createExpenseRequestDto.getFile())
                .build();
        save(expense);
        return true;
    }

    public WorkerExpenseForManager workerExpenseForManager(String managerid) {
        Optional<Expense>expense = expenseRepository.findOptionalByManagerid(managerid);
        WorkerExpenseForManager workerExpenseForManager = WorkerExpenseForManager.builder()
                .expenditureType(expense.get().getExpenditureType())
                .workerid(expense.get().getWorkerid())
                .amountOfExpenditure(expense.get().getAmountOfExpenditure())
                .file(expense.get().getFile())
                .currency(expense.get().getCurrency())
                .replyDate(expense.get().getReplyDate())
                .approvalStatus(expense.get().getApprovalStatus())
                .build();
        return workerExpenseForManager;
    }

    public List<WorkerExpenseForManager> sortByStatus(String managerid, List<WorkerExpenseForManager> workerExpenseForManagers) {
        Optional<Expense>expense = expenseRepository.findOptionalByManagerid(managerid);
        Comparator<WorkerExpenseForManager>workerExpenseForManagerComparator = new Comparator<WorkerExpenseForManager>() {
            @Override
            public int compare(WorkerExpenseForManager o1, WorkerExpenseForManager o2) {
               String [] sortByStatus = {"PENDING_APPROVAL","APPROVED","REJECTED"};
               int index1 = Arrays.asList(sortByStatus).indexOf(o1.getApprovalStatus());
               int index2 = Arrays.asList(sortByStatus).indexOf(o2.getApprovalStatus());
                return Integer.compare(index1,index2);
            }
        };
        Collections.sort(workerExpenseForManagers,workerExpenseForManagerComparator);
        return workerExpenseForManagers;
    }

    public List<ApprovalStatusResponse> getApprovalStatus() {
        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
        expenseRepository.findOptionalByOrderApprovalStatusDesc(PENDING_APPROVAL).forEach(x->{
            approvalStatusResponses.add(ApprovalStatusResponse.builder()
                            .workerid(x.getWorkerid())
                            .approvalStatus(x.getApprovalStatus())
                            .amountOfExpenditure(x.getAmountOfExpenditure())
                            .expenditureType(x.getExpenditureType())
                            .file(x.getFile())
                            .currency(x.getCurrency())
                            .replyDate(x.getReplyDate())
                    .build());
        });
        return approvalStatusResponses;
    }

    public List<ApprovalStatusResponse> getApprovalStatusApproved() {
        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
        expenseRepository.findOptionalByApprovalStatus(APPROVED).forEach(x->{
            approvalStatusResponses.add(ApprovalStatusResponse.builder()
                            .workerid(x.getWorkerid())
                            .approvalStatus(x.getApprovalStatus())
                            .expenditureType(x.getExpenditureType())
                            .amountOfExpenditure(x.getAmountOfExpenditure())
                            .file(x.getFile())
                            .currency(x.getCurrency())
                            .replyDate(x.getReplyDate())
                    .build());
        });
        return approvalStatusResponses;
    }
}
