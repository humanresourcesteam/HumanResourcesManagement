package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.WorkerExpense;
import com.bilgeadam.dto.response.WorkerExpenseForManager;
import com.bilgeadam.repository.IExpenseRepository;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Service
public class ExpenseService extends ServiceManager<Expense, String> {

    private final IExpenseRepository expenseRepository;

    public ExpenseService(IExpenseRepository expenseRepository) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(CreateExpenseRequestDto createExpenseRequestDto) {
        if (createExpenseRequestDto.getFile().isEmpty()) {
            Expense expense = Expense.builder()
                    .managerid(createExpenseRequestDto.getManagerid())
                    .workerid(createExpenseRequestDto.getWorkerid())
                    .expenditureType(createExpenseRequestDto.getExpenditureType())
                    .amountOfExpenditure(createExpenseRequestDto.getAmountOfExpenditure())
                    .currency(createExpenseRequestDto.getCurrency())
                    .approvalStatus(createExpenseRequestDto.getApprovalStatus())
                    .desc(createExpenseRequestDto.getDesc())
                    .name(createExpenseRequestDto.getName())
                    .surname(createExpenseRequestDto.getSurname())
                    .approvalStatus(ApprovalStatus.PENDING_APPROVAL)
                    .requestDate(LocalDate.now())
                    .build();
            save(expense);
            return true;
        }

        List<String> files = new ArrayList<>();
        createExpenseRequestDto.getFile().forEach(x -> {
            files.add(imageUpload(x));
        });
        Expense expense = Expense.builder()
                .managerid(createExpenseRequestDto.getManagerid())
                .workerid(createExpenseRequestDto.getWorkerid())
                .expenditureType(createExpenseRequestDto.getExpenditureType())
                .amountOfExpenditure(createExpenseRequestDto.getAmountOfExpenditure())
                .currency(createExpenseRequestDto.getCurrency())
                .approvalStatus(createExpenseRequestDto.getApprovalStatus())
                .desc(createExpenseRequestDto.getDesc())
                .name(createExpenseRequestDto.getName())
                .file(files)
                .surname(createExpenseRequestDto.getSurname())
                .approvalStatus(ApprovalStatus.PENDING_APPROVAL)
                .requestDate(LocalDate.now())
                .build();
        save(expense);
        return true;
    }
    public String imageUpload(MultipartFile file) {
        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "doa04qdhh");
        config.put("api_key", "261194321947226");
        config.put("api_secret", "K5_9m33MSDBvu4MZuHhHWeFxNeA");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url + " --------------------------");
            return url;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<WorkerExpense> getWorkerExpense(String workerid) {
        List<Expense> expenses = expenseRepository.findByWorkerid(workerid);
        List<WorkerExpense> workerExpenses = new ArrayList<>();
        expenses.forEach(x -> {
            workerExpenses.add(WorkerExpense.builder()
                    .workerid(x.getWorkerid())
                    .replyDate(x.getReplyDate())
                    .managerid(x.getManagerid())
                    .expenditureType(x.getExpenditureType())
                    .amountOfExpenditure(x.getAmountOfExpenditure())
                    .currency(x.getCurrency())
                    .approvalStatus(x.getApprovalStatus())
                    .file(x.getFile())
                    .requestDate(x.getRequestDate())
                    .desc(x.getDesc())
                    .id(x.getId())
                    .build());
        });
        return workerExpenses;
    }

    public List<WorkerExpenseForManager> getExpenseForManager(String managerid) {
        List<Expense> expenses = expenseRepository.findByManagerid(managerid);
        List<WorkerExpenseForManager> workerExpenses = new ArrayList<>();
        expenses.forEach(x -> {
            workerExpenses.add(WorkerExpenseForManager.builder()
                    .workerid(x.getWorkerid())
                    .replyDate(x.getReplyDate())
                    .managerid(x.getManagerid())
                    .expenditureType(x.getExpenditureType())
                    .amountOfExpenditure(x.getAmountOfExpenditure())
                    .currency(x.getCurrency())
                    .approvalStatus(x.getApprovalStatus())
                    .file(x.getFile())
                    .requestDate(x.getRequestDate())
                    .desc(x.getDesc())
                    .id(x.getId())
                    .name(x.getName())
                    .surname(x.getSurname())
                    .build());
        });
        return workerExpenses;
    }
    public Boolean updateExpense(UpdateStatusRequestDto updateStatusRequestDto) {
        Optional<Expense> expense = findById(updateStatusRequestDto.getId());
        expense.get().setApprovalStatus(updateStatusRequestDto.getStatus());
        expense.get().setReplyDate(LocalDate.now());
        update(expense.get());
        return true;
    }
}
