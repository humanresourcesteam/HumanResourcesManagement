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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(API+VERSION+ADMIN )
public class AdminController {

    private final AdminService adminService;

    @PostMapping( UPDATE)
    public ResponseEntity<Boolean> updateInfo( UpdateAdminInfoRequestDto updateRequestDto) {
        return ResponseEntity.ok(adminService.updateInfo(updateRequestDto));
    }
    @GetMapping(SUMMARY)
    public ResponseEntity<List<SummaryResponseDto>>summary(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getSummary(baseRequestDto));
    }
    @GetMapping(DETAIL_INFORMATION)
    public ResponseEntity<List<DetailResponseDto>>detailInformation(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getDetailInformation(baseRequestDto));
    }
    @GetMapping("/getınfo")
    public ResponseEntity<DetailResponseDto>getDetailInformationForAdmin(BaseRequestDto baseRequestDto){
        return ResponseEntity.ok(adminService.getDetailInformationForAdmin(baseRequestDto));
    }
    
    @GetMapping("/apideneme")
    public ResponseEntity<String>apideneme(){
        return ResponseEntity.ok("api deniyoruz");
    }

}
