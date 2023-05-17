package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.AdvanceForWorker;
import com.bilgeadam.dto.response.WorkerAdvanceForManager;
import com.bilgeadam.exception.AdvanceException;
import com.bilgeadam.repository.IAdvanceRepository;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bilgeadam.exception.EErrorType.ADVANCE_HAS_BEEN;
import static com.bilgeadam.repository.enums.ApprovalStatus.PENDING_APPROVAL;

@Service
public class AdvanceService extends ServiceManager<Advance, String> {

    private final IAdvanceRepository advanceRepository;

    public AdvanceService(IAdvanceRepository advanceRepository) {
        super(advanceRepository);
        this.advanceRepository = advanceRepository;
    }

    public boolean createAdvance(CreateAdvanceRequestDto createAdvanceRequestDto) {

        Optional<Advance> advanceOptional = advanceRepository.findOptionalByWorkeridAndApprovalStatus(createAdvanceRequestDto.getWorkerid(), PENDING_APPROVAL);
        if (advanceOptional.isPresent()) throw new AdvanceException(ADVANCE_HAS_BEEN);
        Advance advance = Advance.builder()
                .advanceAmount(createAdvanceRequestDto.getAdvanceAmount())
                .advanceRequestType(createAdvanceRequestDto.getAdvanceRequestType())
                .dateOfRequest(LocalDate.now())
                .managerid(createAdvanceRequestDto.getManagerid())
                .currency(createAdvanceRequestDto.getCurrency())
                .workerid(createAdvanceRequestDto.getWorkerid())
                .nameOfTheRequester(createAdvanceRequestDto.getNameOfTheRequester())
                .surnameOfTheRequester(createAdvanceRequestDto.getSurnameOfTheRequester())
                .description(createAdvanceRequestDto.getDescription())
                .approvalStatus(PENDING_APPROVAL)
                .build();
        save(advance);
        return true;

    }

    public List<AdvanceForWorker> getAdvancesForWorker(String workerid) {
        List<Advance> advances = advanceRepository.findByWorkerid(workerid);
        List<AdvanceForWorker> advanceForWorkers = new ArrayList<>();

        advances.forEach(x -> {
            advanceForWorkers.add(AdvanceForWorker.builder()
                    .approvalStatus(x.getApprovalStatus())
                    .dateOfRequest(x.getDateOfRequest())
                    .replyDate(x.getReplyDate())
                    .description(x.getDescription())
                    .advanceAmount(x.getAdvanceAmount())
                    .currency(x.getCurrency())
                    .advanceRequestType(x.getAdvanceRequestType())
                    .id(x.getId())
                    .build());
        });

        return advanceForWorkers;
    }

    public List<WorkerAdvanceForManager> getAdvanceForManager(String managerid) {
        List<Advance> advances = advanceRepository.findByManagerid(managerid);
        List<WorkerAdvanceForManager> advanceForManagers = new ArrayList<>();
        advances.forEach(x -> {
            advanceForManagers.add(WorkerAdvanceForManager.builder()
                    .managerid(x.getManagerid())
                    .advanceRequestType(x.getAdvanceRequestType())
                    .approvalStatus(x.getApprovalStatus())
                    .advanceAmount(x.getAdvanceAmount())
                    .currency(x.getCurrency())
                    .description(x.getDescription())
                    .replyDate(x.getReplyDate())
                    .surnameOfTheRequester(x.getSurnameOfTheRequester())
                    .nameOfTheRequester(x.getNameOfTheRequester())
                    .workerid(x.getWorkerid())
                    .id(x.getId())
                    .currency(x.getCurrency())
                    .dateOfRequest(x.getDateOfRequest())
                    .build());
        });
        return advanceForManagers;
    }

    public Boolean updateStatus(UpdateStatusRequestDto updateStatusRequestDto) {

        Optional<Advance> advanceOptional = findById(updateStatusRequestDto.getId());
        advanceOptional.get().setApprovalStatus(updateStatusRequestDto.getStatus());
        advanceOptional.get().setReplyDate(LocalDate.now());
        update(advanceOptional.get());
        return true;
    }


//    public WorkerAdvanceForManager workerAdvanceForManager(String managerid) {
//        Optional<Advance>advanceOptional = advanceRepository.findOptionalByManagerid(managerid);
//        WorkerAdvanceForManager workerAdvanceForManager = WorkerAdvanceForManager.builder()
//                .advanceAmount(advanceOptional.get().getAdvanceAmount())
//                .workerid(advanceOptional.get().getWorkerid())
//                .approvalStatus(advanceOptional.get().getApprovalStatus())
//                .nameOfTheRequester(advanceOptional.get().getNameOfTheRequester())
//                .surnameOfTheRequester(advanceOptional.get().getSurnameOfTheRequester())
//                .replyDate(advanceOptional.get().getReplyDate())
//                .description(advanceOptional.get().getDescription())
//                .currency(advanceOptional.get().getCurrency())
//                .advanceRequestType(advanceOptional.get().getAdvanceRequestType())
//                .dateOfRequest(advanceOptional.get().getDateOfRequest())
//                .build();
//        return workerAdvanceForManager;
//    }
//
//    public List<WorkerAdvanceForManager> sortByStatus(String managerid, List<WorkerAdvanceForManager> workerAdvanceForManagers) {
//        Optional<Advance>advanceOptional = advanceRepository.findOptionalByManagerid(managerid);
//        Comparator<WorkerAdvanceForManager>workerAdvanceForManagerComparator = new Comparator<WorkerAdvanceForManager>() {
//            @Override
//            public int compare(WorkerAdvanceForManager o1, WorkerAdvanceForManager o2) {
//                String [] sortByStatus = {"PENDING_APPROVAL","APPROVED","REJECTED"};
//                int index1 = Arrays.asList(sortByStatus).indexOf(o1.getApprovalStatus());
//                int index2 = Arrays.asList(sortByStatus).indexOf(o2.getApprovalStatus());
//                return Integer.compare(index1,index2);
//            }
//        };
//        Collections.sort(workerAdvanceForManagers,workerAdvanceForManagerComparator);
//        return workerAdvanceForManagers;
//    }
//
//    public List<ApprovalStatusResponse> getApprovalStatus() {
//        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
//        advanceRepository.findOptionalByOrderApprovalStatusDesc(PENDING_APPROVAL).forEach(x->{
//            approvalStatusResponses.add(ApprovalStatusResponse.builder()
//                            .workerid(x.getWorkerid())
//                            .approvalStatus(x.getApprovalStatus())
//                            .advanceRequestType(x.getAdvanceRequestType())
//                            .replyDate(x.getReplyDate())
//                            .currency(x.getCurrency())
//                            .surnameOfTheRequester(x.getSurnameOfTheRequester())
//                            .nameOfTheRequester(x.getNameOfTheRequester())
//                            .dateOfRequest(x.getDateOfRequest())
//                            .advanceAmount(x.getAdvanceAmount())
//                            .description(x.getDescription())
//                            .managerid(x.getManagerid())
//                    .build());
//        });
//        return approvalStatusResponses;
//    }
}
