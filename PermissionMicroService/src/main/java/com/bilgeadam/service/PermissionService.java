package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreatePermissionRequestDto;
import com.bilgeadam.dto.request.UpdateStatusRequestDto;
import com.bilgeadam.dto.response.WorkerPermissionForManager;
import com.bilgeadam.dto.response.WorkerPermissionForWorker;
import com.bilgeadam.repository.IPermissionRepository;
import com.bilgeadam.repository.entity.Permission;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bilgeadam.repository.enums.ApprovalStatus.PENDING_APPROVAL;

@Service
public class PermissionService extends ServiceManager<Permission, String> {

    private final IPermissionRepository permissionRepository;

    public PermissionService(IPermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository = permissionRepository;
    }

//    public RequestForPermissionRequestDto getTheRequestForPermission(PermissionRequestModel permissionRequestModel) {
//        RequestForPermissionRequestDto requestForPermissionRequestDto = new RequestForPermissionRequestDto();
//        requestForPermissionRequestDto.setWorkerid(permissionRequestModel.getWorkerid());
//        requestForPermissionRequestDto.setWorkerName(permissionRequestModel.getWorkerName());
//        requestForPermissionRequestDto.setTypeOfPermit(permissionRequestModel.getTypeOfPermit());
//        requestForPermissionRequestDto.set
//    }

    public boolean createPermission(CreatePermissionRequestDto createPermissionRequestDto) {
        Permission permission = Permission.builder()
                .approvalStatus(PENDING_APPROVAL)
                .dateOfRequest(LocalDate.now())
                .endDate(createPermissionRequestDto.getEndDate())
                .managerid(createPermissionRequestDto.getManagerid())
                .workerid(createPermissionRequestDto.getWorkerid())
                .startDate(createPermissionRequestDto.getStartDate())
                .typeOfPermit(createPermissionRequestDto.getTypeOfPermit())
                .numberOfDays(createPermissionRequestDto.getNumberOfDays())
                .name(createPermissionRequestDto.getName())
                .surname(createPermissionRequestDto.getSurname())
                .build();
        save(permission);
        return true;
    }

//    public WorkerPermissionForManager workerPermissionForManager(String managerid) {
//        Optional<Permission> permission = permissionRepository.findOptionalByManagerid(managerid);
//        WorkerPermissionForManager workerPermissionForManager = WorkerPermissionForManager.builder()
//                .approvalStatus(permission.get().getApprovalStatus())
//                .typeOfPermit(permission.get().getTypeOfPermit())
//                .startDate(permission.get().getStartDate())
//                .replyDate(permission.get().getReplyDate())
//                .endDate(permission.get().getEndDate())
//                .numberOfDays(permission.get().getNumberOfDays())
//                .dateOfRequest(permission.get().getDateOfRequest())
//                .workerid(permission.get().getWorkerid())
//                .build();
//        return workerPermissionForManager;
//    }
//
//    // SU 2 METHODU EMAILDEN DOLAYI CALISTIRAMADIM.. CALISIP CALISMADIKLARINDAN EMIN DEGILIM ,, METODLARI KENDIM YAZAYIM DIYORSAN BU METHODU YORUMA ALIRSIN ...
//    public List<WorkerPermissionForManager> sortByStatus(String managerId, List<WorkerPermissionForManager> workerPermissionForManagers) {
//        Optional<Permission> permission = permissionRepository.findOptionalByManagerid(managerId);
//
//        Comparator<WorkerPermissionForManager> workerPermissionComparator = new Comparator<WorkerPermissionForManager>() {
//            @Override
//            public int compare(WorkerPermissionForManager o1, WorkerPermissionForManager o2) {
//                String[] sortByStatus = {"PENDING_APPROVAL", "APPROVED", "REJECTED"};
//                int index1 = Arrays.asList(sortByStatus).indexOf(o1.getApprovalStatus());
//                int index2 = Arrays.asList(sortByStatus).indexOf(o2.getApprovalStatus());
//                return Integer.compare(index1, index2);
//            }
//        };
//
//        Collections.sort(workerPermissionForManagers, workerPermissionComparator);
//        return workerPermissionForManagers;
//    }

    public List<WorkerPermissionForWorker> getPermissionsForWorker(String workerid) {
        List<Permission> permission = permissionRepository.findOptionalByWorkerid(workerid);
        List<WorkerPermissionForWorker> workers = new ArrayList<>();
        permission.forEach(x -> {
            workers.add(WorkerPermissionForWorker.builder()
                    .dateOfRequest(x.getDateOfRequest())
                    .approvalStatus(x.getApprovalStatus())
                    .startDate(x.getStartDate())
                    .endDate(x.getEndDate())
                    .typeOfPermit(x.getTypeOfPermit())
                    .numberOfDays(x.getNumberOfDays())
                    .replyDate(x.getReplyDate())
                    .id(x.getId())
                    .build());
        });


        return workers;
    }

    public List<WorkerPermissionForManager> getPermissionForManager(String managerid) {
        List<Permission> permissions = permissionRepository.findOptionalByManagerid(managerid);
        List<WorkerPermissionForManager> managers = new ArrayList<>();
        permissions.forEach(x -> {
            managers.add(WorkerPermissionForManager.builder()
                    .dateOfRequest(x.getDateOfRequest())
                    .approvalStatus(x.getApprovalStatus())
                    .startDate(x.getStartDate())
                    .endDate(x.getEndDate())
                    .typeOfPermit(x.getTypeOfPermit())
                    .numberOfDays(x.getNumberOfDays())
                    .replyDate(x.getReplyDate())
                    .id(x.getId())
                    .name(x.getName())
                    .surname(x.getSurname())
                    .build());
        });
        return managers;
    }

    public Boolean updateStatus(UpdateStatusRequestDto updateStatusRequestDto) {

        Optional<Permission> permission = findById(updateStatusRequestDto.getId());
        permission.get().setApprovalStatus(updateStatusRequestDto.getStatus());
        permission.get().setReplyDate(LocalDate.now());
        update(permission.get());
        return true;

    }


//    // listeleme yapılırken desc pending öncelikli olacak
//    public List<ApprovalStatusResponse> getApprovalStatus() {
//        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
//
//        permissionRepository.findOptionalByOrderApprovalStatusDesc().forEach(x->{
//            approvalStatusResponses.add(ApprovalStatusResponse.builder()
//                            .workerid(x.getWorkerid())
//                            .dateOfRequest(x.getDateOfRequest())
//                            .typeOfPermit(x.getTypeOfPermit())
//                            .numberOfDays(x.getNumberOfDays())
//                            .startDate(x.getStartDate())
//                            .endDate(x.getEndDate())
//                            .approvalStatus(x.getApprovalStatus())
//                            .numberOfDays(x.getNumberOfDays())
//                    .build());
//        });
//        return approvalStatusResponses;
//    }
//
//    // onaylı olanları listele ?
//    public List<ApprovalStatusResponse> getApprovalStatusApproved() {
//        List<ApprovalStatusResponse>approvalStatusResponses = new ArrayList<>();
//
//        permissionRepository.findOptionalByApprovalStatus().forEach(x->{
//            approvalStatusResponses.add(ApprovalStatusResponse.builder()
//                    .workerid(x.getWorkerid())
//                    .dateOfRequest(x.getDateOfRequest())
//                    .approvalStatus(x.getApprovalStatus())
//                    .typeOfPermit(x.getTypeOfPermit())
//                    .numberOfDays(x.getNumberOfDays())
//                    .startDate(x.getStartDate())
//                    .endDate(x.getEndDate())
//                    .numberOfDays(x.getNumberOfDays())
//                    .build());
//        });
//        return approvalStatusResponses;
//    }


}
