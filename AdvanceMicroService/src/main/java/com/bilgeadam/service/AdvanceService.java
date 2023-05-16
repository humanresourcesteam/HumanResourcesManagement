package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.response.ApprovalStatusResponse;
import com.bilgeadam.dto.response.WorkerAdvanceForManager;
import com.bilgeadam.exception.AdvanceException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.repository.IAdvanceRepository;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.bilgeadam.repository.enums.ApprovalStatus.PENDING_APPROVAL;

@Service
public class AdvanceService extends ServiceManager<Advance,String > {

    private final IAdvanceRepository advanceRepository;

    public AdvanceService(IAdvanceRepository advanceRepository) {
        super(advanceRepository);
        this.advanceRepository = advanceRepository;
    }

    public boolean createAdvance(CreateAdvanceRequestDto createAdvanceRequestDto) {
        // ÖNCESİNDE AVANS TALEP ETTIYSE HATA FIRLATACAK.
        Optional<Advance>advanceOptional = advanceRepository.findById(createAdvanceRequestDto.getId());
        if (advanceOptional.isPresent()) throw new AdvanceException(EErrorType.ADVANCE_HAS_BEEN);
        Advance advance = Advance.builder()
                .advanceAmount(createAdvanceRequestDto.getAdvanceAmount())
                .advanceRequestType(createAdvanceRequestDto.getAdvanceRequestType())
                .dateOfRequest(createAdvanceRequestDto.getDateOfRequest())
                .managerid(createAdvanceRequestDto.getManagerid())
                .currency(createAdvanceRequestDto.getCurrency())
                .workerid(createAdvanceRequestDto.getWorkerid())
                .nameOfTheRequester(createAdvanceRequestDto.getNameOfTheRequester())
                .surnameOfTheRequester(createAdvanceRequestDto.getSurnameOfTheRequester())
                .description(createAdvanceRequestDto.getDescription())
                .replyDate(createAdvanceRequestDto.getReplyDate())
                .approvalStatus(createAdvanceRequestDto.getApprovalStatus())
                .build();
        save(advance);
        return true;

    }


    public WorkerAdvanceForManager workerAdvanceForManager(String managerid) {
        Optional<Advance>advanceOptional = advanceRepository.findOptionalByManagerid(managerid);
        WorkerAdvanceForManager workerAdvanceForManager = WorkerAdvanceForManager.builder()
                .advanceAmount(advanceOptional.get().getAdvanceAmount())
                .workerid(advanceOptional.get().getWorkerid())
                .approvalStatus(advanceOptional.get().getApprovalStatus())
                .nameOfTheRequester(advanceOptional.get().getNameOfTheRequester())
                .surnameOfTheRequester(advanceOptional.get().getSurnameOfTheRequester())
                .replyDate(advanceOptional.get().getReplyDate())
                .description(advanceOptional.get().getDescription())
                .currency(advanceOptional.get().getCurrency())
                .advanceRequestType(advanceOptional.get().getAdvanceRequestType())
                .dateOfRequest(advanceOptional.get().getDateOfRequest())
                .build();
        return workerAdvanceForManager;
    }

    public List<WorkerAdvanceForManager> sortByStatus(String managerid, List<WorkerAdvanceForManager> workerAdvanceForManagers) {
        Optional<Advance>advanceOptional = advanceRepository.findOptionalByManagerid(managerid);
        Comparator<WorkerAdvanceForManager>workerAdvanceForManagerComparator = new Comparator<WorkerAdvanceForManager>() {
            @Override
            public int compare(WorkerAdvanceForManager o1, WorkerAdvanceForManager o2) {
                String [] sortByStatus = {"PENDING_APPROVAL","APPROVED","REJECTED"};
                int index1 = Arrays.asList(sortByStatus).indexOf(o1.getApprovalStatus());
                int index2 = Arrays.asList(sortByStatus).indexOf(o2.getApprovalStatus());
                return Integer.compare(index1,index2);
            }
        };
        Collections.sort(workerAdvanceForManagers,workerAdvanceForManagerComparator);
        return workerAdvanceForManagers;
    }

    public List<ApprovalStatusResponse> getApprovalStatus() {
        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
        advanceRepository.findOptionalByOrderApprovalStatusDesc(PENDING_APPROVAL).forEach(x->{
            approvalStatusResponses.add(ApprovalStatusResponse.builder()
                            .workerid(x.getWorkerid())
                            .approvalStatus(x.getApprovalStatus())
                            .advanceRequestType(x.getAdvanceRequestType())
                            .replyDate(x.getReplyDate())
                            .currency(x.getCurrency())
                            .surnameOfTheRequester(x.getSurnameOfTheRequester())
                            .nameOfTheRequester(x.getNameOfTheRequester())
                            .dateOfRequest(x.getDateOfRequest())
                            .advanceAmount(x.getAdvanceAmount())
                            .description(x.getDescription())
                            .managerid(x.getManagerid())
                    .build());
        });
        return approvalStatusResponses;
    }
}
