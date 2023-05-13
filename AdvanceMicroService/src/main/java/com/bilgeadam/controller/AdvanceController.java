package com.bilgeadam.controller;

import com.bilgeadam.service.AdvanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.bilgeadam.constant.EndPoints.*;
@RestController
@RequestMapping(API+VERSION+ADVANCE)
@RequiredArgsConstructor
public class AdvanceController {

    private final AdvanceService advanceService;

}
