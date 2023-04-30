package com.bilgeadam.controller;


import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.request.UpdateAdminInfoRequestDto;
import com.bilgeadam.dto.response.DetailResponseDto;
import com.bilgeadam.dto.response.SummaryResponseDto;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @GetMapping(SUMMARY)
    public ResponseEntity<List<SummaryResponseDto>>summary(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getSummary(baseRequestDto));
    }

    @GetMapping(DETAIL_INFORMATION)
    public ResponseEntity<List<DetailResponseDto>>detailInformation(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getDetailInformation(baseRequestDto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String>updateInfo(@RequestBody UpdateAdminInfoRequestDto updateRequestDto){
        return ResponseEntity.ok(adminService.updateInfo(updateRequestDto));
    }

    @GetMapping("/getınfo")
    public ResponseEntity<DetailResponseDto>getDetailInformationForAdmin(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getDetailInformationForAdmin(baseRequestDto));
    }
}
